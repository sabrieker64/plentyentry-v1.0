package at.commodussolutions.plentyentry.ordermanagement.event.service;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    List<Event> getAllMaintainedEvents();

    Event createNewEvent(Event event) throws IOException;

    Event getEventById(Long id);

    Event updateEventById(Event event, Boolean changeTicketCounts) throws IOException;

    Page<Event> getEventsByCriteria(String criteria);

    Long countHowMuchTicketisLeft(Long eventId);

}
