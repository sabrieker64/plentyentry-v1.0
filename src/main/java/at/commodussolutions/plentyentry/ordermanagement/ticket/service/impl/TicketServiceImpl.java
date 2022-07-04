package at.commodussolutions.plentyentry.ordermanagement.ticket.service.impl;
/**
 * Author: @Mina
 */

import at.commodussolutions.plentyentry.ordermanagement.event.service.EventService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private EventService eventService;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getBoughtTickets() {
        return userService.getUserByJWTToken().getShoppingCart().getTickets().stream()
                .filter(status -> status.getTicketStatus().equals(TicketStatus.SELLED)
                        || !status.getTicketStatus().equals(TicketStatus.EXPIRED))
                .collect(Collectors.toList());
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.getById(id);
    }

    @Override
    public Ticket updateTicketById(Ticket updatedTicket) {
        return ticketRepository.save(updatedTicket);
    }

    @Override
    public Ticket createNewTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void putTicketsToShoppingCart(List<Ticket> ticketSet) {
        User user = userService.getUserByJWTToken();
        boolean addedAllTicketsToShoppingCart = user.getShoppingCart().getTickets().addAll(ticketSet);
        if (addedAllTicketsToShoppingCart) {
            ticketSet.forEach(ticket -> ticket.setTicketStatus(TicketStatus.RESERVED));
            log.info("Added to shoppingcart");
            // userRepository.save(user);
        } else {
            log.error("Cant add tickets to shoppingcart");
        }
    }

    @Override
    public List<Ticket> getTicketByEventId(Long eventId, Long quantity) {
        var ticketList = new ArrayList<Ticket>();
        var ticketsWithTheRightStatus = ticketRepository.getAllByEventId(eventId).stream()
                .filter(ticket -> ticket.getTicketStatus().equals(TicketStatus.NOTSELLED)).collect(Collectors.toList());
        for (var ticketIndex = 1; ticketIndex <= quantity; ticketIndex++) {
            ticketList.add(ticketsWithTheRightStatus.get(ticketIndex));
        }
        return ticketList;
    }

    @Override
    public void createAutomaticTicketsForNewEvent(Long eventId, Long quantity) {
        var currentEvent = eventService.getEventById(eventId);
        for (var index = 1; index <= quantity; index++) {
            var newTicket = new Ticket();
            newTicket.setEvent(currentEvent);
            newTicket.setTicketStatus(TicketStatus.NOTSELLED);
            ticketRepository.save(newTicket);
        }
    }

    @Override
    public List<Ticket> getTicketForEventAndAddToCartByQuantity(Long eventId, Long quantity) {
        var tickets = this.getTicketByEventId(eventId, quantity);
        putTicketsToShoppingCart(tickets);
        var addedTickets = userService.getUserByJWTToken().getShoppingCart().getTickets();
        if (addedTickets.size() >= tickets.size()) {
            log.info("Tickets for event with id " + eventId + " and quantity " + quantity + " added to your shoppingcart with" +
                    " id " + userService.getUserByJWTToken().getShoppingCart().getId() + " succesfully");
            return addedTickets;
        }
        throw new RuntimeException("Tickets adding to shoppingcart failed!");
    }

    @Override
    public void excludeTicketsFromShoppingcart(Long eventId) {
        getBoughtTickets().stream().filter(ticket -> Objects.equals(ticket.getEvent().getId(), eventId))
                .forEach(ticket -> {
                    ticket.setTicketStatus(TicketStatus.NOTSELLED);
                    //todo hier nochj bitte die tickets aus der sjoppingcart entfernen
                });
    }

    @Override
    public List<Ticket> findAllTicketsThatAreNotAvailableAnymore(Long eventId) {
        return ticketRepository.getAllByEventId(eventId);
    }

    @Override
    public void updateBoughtTickets(List<Ticket> tickets) {
        tickets.forEach(ticket -> {
            ticket.setTicketStatus(TicketStatus.SELLED);
            ticketRepository.save(ticket);
        });
    }
}
