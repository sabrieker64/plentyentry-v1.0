package at.commodussolutions.plentyentry.ordermanagement.event.service.impl;
/**
 * Author: @Mina
 */


import at.commodussolutions.plentyentry.ordermanagement.event.aws.service.AmazonClient;
import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import at.commodussolutions.plentyentry.ordermanagement.event.service.EventService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private AmazonClient amazonClient;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllMaintainedEvents() {

        User user = userService.getUserByJWTToken();

        if (user.getUserType().equals(UserType.MAINTAINER) || user.getUserType().equals(UserType.SUPERADMIN) || user.getUserType().equals(UserType.ADMIN)) {
            Set<Event> maintainedEvents = user.getEntertainedEvents();
            return new ArrayList<>(maintainedEvents);
        }

        throw new NotFoundException("Sie haben keine Veranstaltungen");

    }

    @Override
    public Event createNewEvent(Event event) {
        var savedEvent = eventRepository.save(event);
        ticketService.createAutomaticTicketsForNewEvent(savedEvent.getId(), savedEvent.getTicketCounter());
        //amazonClient.uploadFiles()
        return savedEvent;
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.getById(id);
    }

    @Override
    public Event updateEventById(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Page<Event> getEventsByCriteria(String criteria) {
        return null;
    }

    @Override
    public Long countHowMuchTicketisLeft(Long eventId) {
        var event = eventRepository.getById(eventId);
        var allTickets = event.getTicketCounter();
        var alreadySolOrNotAvaillableAnymore =
                ticketService.findAllTicketsThatAreNotAvailableAnymore(eventId).stream().count();
        Long.parseLong(String.valueOf(alreadySolOrNotAvaillableAnymore));


        // todo ein eigenes object DTO für die ShoppingCart die von einer listen von tickets beschmückt ist und die
        //  quiantity hat und den preis
        return null;
    }
}
