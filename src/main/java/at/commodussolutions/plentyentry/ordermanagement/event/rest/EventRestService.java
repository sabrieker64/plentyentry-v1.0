package at.commodussolutions.plentyentry.ordermanagement.event.rest;


import at.commodussolutions.plentyentry.backendConfig.utils.PESecured;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static at.commodussolutions.plentyentry.user.userdata.enums.UserType.*;

@RestController
@RequestMapping("/api/backend/event")
@CrossOrigin
public interface EventRestService {

    @GetMapping("/list")
    @ResponseBody
    @PESecured({ADMIN, SUPERADMIN, MAINTAINER})
    List<EventDTO> getAllEvents();

    @GetMapping("/list/maintainedEvents")
    @ResponseBody
    List<EventDTO> getAllMaintainedEvents();

    @GetMapping("/{id}")
    @ResponseBody
    EventDTO getEventById(@PathVariable Long id);

    @PutMapping()
    @ResponseBody
    EventDTO updateEventById(@RequestBody EventDTO updatedEvent);

    @PostMapping()
    @ResponseBody
    EventDTO createNewEvent(@RequestBody EventDTO eventDTO);
}
