package at.commodussolutions.plentyentry.ordermanagement.event.service;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    Event createNewEvent(Event event);

    Event getEventById(Long id);

    Event updateEventById(Event event);

}
