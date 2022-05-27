package at.commodussolutions.plentyentry.ordermanagement.event.rest;


import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backend/event")
@CrossOrigin
public interface EventRestService {

    @GetMapping("/list")
    @ResponseBody
    List<EventDTO> getAllEvents();


    @GetMapping("/{id}")
    @ResponseBody
    EventDTO getEventById(@PathVariable Long id);


}
