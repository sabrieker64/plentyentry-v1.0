package at.commodussolutions.plentyentry.ordermanagement.event.service.impl;
/**
 * Author: @Mina
 */


import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import at.commodussolutions.plentyentry.ordermanagement.event.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event createNewEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.getById(id);
    }

    @Override
    public Event updateEventById(Event event) {
        return eventRepository.save(event);
    }
}
