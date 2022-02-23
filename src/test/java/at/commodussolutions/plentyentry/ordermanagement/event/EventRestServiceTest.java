package at.commodussolutions.plentyentry.ordermanagement.event;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.dbInit.EventInitializer;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.mapper.EventMapper;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import at.commodussolutions.plentyentry.user.userdata.dbInit.UserInitializer;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.imap.protocol.ID;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class EventRestServiceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventRepository eventRepository;

    private final static String baseUrl = "/api/backend/event";

    @Autowired
    private EventInitializer eventInitializer;

    @Autowired
    private EventMapper eventMapper;

    @BeforeEach
    void createData() {
        if (eventInitializer.shouldDataBeInitialized()) {
            eventInitializer.initData();
        }
    }


    @Test
    void getAllEventTest() throws Exception {
        var getAllEvents = eventRepository.findAll();
        var firstResult = getAllEvents.get(0);



        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/list")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<EventDTO> resultList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<EventDTO>>() {
                });


        Assertions.assertEquals(firstResult.getCity(), resultList.get(0).getCity());
    }

    @Test
    void getEventById() throws Exception {
        var firstEvent = eventRepository.findById(1L).orElse(null);
        // WHY DO I HAVE TO USE VAR???
        //Hibernate.initialize(firstEvent.getEventImageUrls());
        //EventDTO firstEventDTO = eventMapper.mapToDTO(firstEvent);


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        EventDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<EventDTO>() {
                });


        Assertions.assertEquals(firstEvent.getCity(), result.getCity());
    }


    //DOES NOT WORK
    @Test
    void updateEventById() throws Exception {

        var firstEvent = eventRepository.findById(1L).orElse(null);
        firstEvent.setCity("KITZBICHI");

        MvcResult updateFirstEvent = mvc.perform(MockMvcRequestBuilders.put(baseUrl,firstEvent)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        EventDTO resultOfUpdatedEvent = objectMapper.readValue(updateFirstEvent.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<EventDTO>() {
                });




        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        EventDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<EventDTO>() {
                });


        Assertions.assertEquals(resultOfUpdatedEvent.getCity(), result.getCity());
    }

    @Test
    void createNewEvent() throws Exception {

        ArrayList<String> eventImageUrls = new ArrayList<>();

        eventImageUrls.add("abc");

        EventDTO newEvent = new EventDTO();
        newEvent.setName("SWINGER PARTY");
        newEvent.setDate(LocalDate.now());
        newEvent.setDescription("Für jeden Schicker eine Eskalation!");
        newEvent.setPrice(10.00);
        newEvent.setTicketCounter(4);
        newEvent.setTicketId(5L);
        newEvent.setAddress("Schicker Blowis");
        newEvent.setCity("Fieberbrooklyn");
        newEvent.setEventImageUrls(eventImageUrls);

        MvcResult postNewEvent = mvc.perform(MockMvcRequestBuilders.post(baseUrl,newEvent)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        EventDTO resultOfNewEvent = objectMapper.readValue(postNewEvent.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<EventDTO>() {
                });



        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/3")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        EventDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<EventDTO>() {
                });


        Assertions.assertEquals(resultOfNewEvent.getCity(), result.getCity());
    }

}
