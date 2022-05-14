package at.commodussolutions.plentyentry.ordermanagement.event.rest.impl;
/**
 * Author: @Mina
 */

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.mapper.EventMapper;
import at.commodussolutions.plentyentry.ordermanagement.event.rest.EventRestService;
import at.commodussolutions.plentyentry.ordermanagement.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventRestServiceImpl implements EventRestService {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<EventDTO> getAllEvents() {
        return eventMapper.mapToListDTO(eventService.getAllEvents());
    }

    @Override
    public List<EventDTO> getAllMaintainedEvents() {
        return eventMapper.mapToListDTO(eventService.getAllMaintainedEvents());
    }

    @Override
    public EventDTO getEventById(Long id) {
        return eventMapper.mapToDTO(eventService.getEventById(id));
    }

    @Override
    public EventDTO updateEventById(EventDTO updatedEvent) {
        Event event = eventService.getEventById(updatedEvent.getId());
        eventMapper.mapToEntity(updatedEvent, event);
        return eventMapper.mapToDTO(eventService.updateEventById(event));
    }

    @Override
    public EventDTO createNewEvent(EventDTO eventDTO) {
        Event event = new Event();
        eventMapper.mapToEntity(eventDTO, event);
        event = eventService.createNewEvent(event);
        return eventMapper.mapToDTO(event);
    }
}
