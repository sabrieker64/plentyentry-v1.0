package event;

import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EventRestServiceTest {

    /*
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EventRepository eventRepository;

    private String baseUrl = "/api/backend/event";

    @Test
    @SneakyThrows
    public void processTesting() throws Exception {
        MvcResult getAllEvents = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/list")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        //List<EventDTO> resultList = objectMapper.readValue(getAllEvents.getResponse().getContentAsString(), );




    }

     */


}
