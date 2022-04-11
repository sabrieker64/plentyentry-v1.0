package at.commodussolutions.plentyentry.ordermanagement.ticket;

import at.commodussolutions.plentyentry.ordermanagement.event.dbInit.EventInitializer;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.mapper.EventMapper;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dbInit.TicketInitializer;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.user.userdata.dbInit.UserInitializer;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@Transactional
public class TicketRestServiceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    private final static String baseUrl = "/api/backend/ticket";

    @Autowired
    private TicketInitializer ticketInitializer;

    @Autowired
    private EventInitializer eventInitializer;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private UserInitializer userInitializer;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void createData() {
        if (ticketInitializer.shouldDataBeInitialized()) {
            ticketInitializer.initData();
        }
        if (eventInitializer.shouldDataBeInitialized()) {
            eventInitializer.initData();
        }
        if (userInitializer.shouldDataBeInitialized()) {
            userInitializer.initData();
        }
    }

    @Test
    void getAllTicketsTest() throws Exception {

        var firstTicket = ticketRepository.findAll().get(0);

        //STACKOVERFLOW????
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/list")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TicketDTO> resultList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<List<TicketDTO>>() {
                });

        Assertions.assertEquals(firstTicket.getEvent(), resultList.get(0).getEvent());

    }

    @Test
    void getTicketByID() throws Exception {
        var firstTicket = ticketRepository.findAll().get(0);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/"+firstTicket.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TicketDTO resultTicket = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<TicketDTO>() {
                });

        Assertions.assertEquals(firstTicket.getEvent(), resultTicket.getEvent());

    }

    @Test
    void updateTicketByID() throws Exception {
        var firstTicket = ticketRepository.findAll().get(0);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(baseUrl)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstTicket)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TicketDTO resultTicket = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<TicketDTO>() {
                });

        Assertions.assertEquals(firstTicket.getEvent(), resultTicket.getEvent());

    }

    @Test
    void createTicket() throws Exception {

        var ticket = new TicketDTO();
        var event = eventRepository.findAll().get(0);
        var user = userRepository.findAll().get(0);

        ticket.setTicketStatus(TicketStatus.NOTSELLED);
        ticket.setEvent(eventMapper.mapToDTO(event));
        ticket.setQuantity(100);
        ticket.setUser(userMapper.mapToDTO(user));

        //STACKOVERFLOW????
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TicketDTO resultTicket = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<TicketDTO>() {
                });

        Assertions.assertEquals(ticket.getEvent(), resultTicket.getEvent());

    }



}
