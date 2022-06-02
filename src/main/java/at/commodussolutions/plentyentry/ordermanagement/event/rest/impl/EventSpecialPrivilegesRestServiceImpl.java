package at.commodussolutions.plentyentry.ordermanagement.event.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.mapper.EventMapper;
import at.commodussolutions.plentyentry.ordermanagement.event.rest.EventSpecialPrivilegesRestService;
import at.commodussolutions.plentyentry.ordermanagement.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class EventSpecialPrivilegesRestServiceImpl implements EventSpecialPrivilegesRestService {

    @Autowired
    EventMapper eventMapper;

    @Autowired
    EventService eventService;

    @Override
    public List<EventDTO> getAllMaintainedEvents() {
        return eventMapper.mapToListDTO(eventService.getAllMaintainedEvents());
    }

    @Override
    public EventDTO updateEventById(EventDTO updatedEvent) throws IOException {
        Event event = eventService.getEventById(updatedEvent.getId());
        eventMapper.mapToEntity(updatedEvent, event);
        return eventMapper.mapToDTO(eventService.updateEventById(event));
    }

    @Override
    public EventDTO createNewEvent(EventDTO eventDTO) throws IOException {
        Event event = new Event();
        eventMapper.mapToEntity(eventDTO, event);
        event = eventService.createNewEvent(event);
        return eventMapper.mapToDTO(event);
    }
}
