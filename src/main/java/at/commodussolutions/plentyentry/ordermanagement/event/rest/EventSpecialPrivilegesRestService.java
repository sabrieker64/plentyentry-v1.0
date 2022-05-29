package at.commodussolutions.plentyentry.ordermanagement.event.rest;

import at.commodussolutions.plentyentry.backendConfig.utils.PESecured;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static at.commodussolutions.plentyentry.user.userdata.enums.UserType.*;

@RestController
@RequestMapping(path = "/api/backend/event/special-privileges")
@CrossOrigin
public interface EventSpecialPrivilegesRestService {

    @GetMapping("/list/maintainedEvents")
    @ResponseBody
    @PESecured({ADMIN, SUPERADMIN, MAINTAINER})
    List<EventDTO> getAllMaintainedEvents();

    @PutMapping()
    @ResponseBody
    @PESecured({ADMIN, SUPERADMIN, MAINTAINER})
    EventDTO updateEventById(@RequestBody EventDTO updatedEvent);

    @PostMapping()
    @ResponseBody
    @PESecured({ADMIN, SUPERADMIN, MAINTAINER})
    EventDTO createNewEvent(@RequestBody EventDTO eventDTO) throws IOException;
}
