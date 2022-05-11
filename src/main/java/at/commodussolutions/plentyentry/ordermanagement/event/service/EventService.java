package at.commodussolutions.plentyentry.ordermanagement.event.service;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    List<Event> getAllMaintainedEvents();

    Event createNewEvent(Event event);

    Event getEventById(Long id);

    Event updateEventById(Event event);

    Page<Event> getEventsByCriteria(String criteria);

}
