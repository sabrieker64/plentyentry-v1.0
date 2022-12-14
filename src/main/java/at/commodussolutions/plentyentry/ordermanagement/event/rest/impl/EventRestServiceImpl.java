package at.commodussolutions.plentyentry.ordermanagement.event.rest.impl;
/**
 * Author: @Mina
 */

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
    public EventDTO getEventById(Long id) {
        return eventMapper.mapToDTO(eventService.getEventById(id));
    }


}
