package at.commodussolutions.plentyentry.ordermanagement.event.rest;


import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backend/event")
public interface EventRestService {

    @GetMapping("/list")
    @ResponseBody
    List<EventDTO> getAllEvents();

    @PostMapping()
    @ResponseBody
    EventDTO createNewEvent(@RequestBody EventDTO eventDTO);
}
