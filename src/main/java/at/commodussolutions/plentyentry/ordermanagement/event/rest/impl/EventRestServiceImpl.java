package at.commodussolutions.plentyentry.ordermanagement.event.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.mapper.EventMapper;
import at.commodussolutions.plentyentry.ordermanagement.event.rest.EventRestService;
import at.commodussolutions.plentyentry.ordermanagement.event.service.EventService;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventRestServiceImpl implements EventRestService {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventMapper eventMapper;


    @Override
    public List<EventDTO> getAllEvents() {
        return eventMapper.mapToListDTO(eventService.getAllEvents());
    }

    @Override
    public EventDTO createNewEvent(EventDTO eventDTO) {
        Event event = new Event();
        eventMapper.mapToEntity(eventDTO, event);
        event = eventService.createNewEvent(event);
        return eventMapper.mapToDTO(event);
    }
}
