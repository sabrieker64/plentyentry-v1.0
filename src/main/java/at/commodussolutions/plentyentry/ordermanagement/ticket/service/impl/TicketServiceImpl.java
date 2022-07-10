package at.commodussolutions.plentyentry.ordermanagement.ticket.service.impl;
/**
 * Author: @Mina
 */

import at.commodussolutions.plentyentry.ordermanagement.event.service.EventService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketsToRemove;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import com.stripe.model.Token;
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

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getBoughtTickets() {
        return userService.getUserByJWTToken().getShoppingCart().getTickets().stream()
                .filter(status -> status.getTicketStatus().equals(TicketStatus.SELLED))
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
        for (var ticketIndex = 0; ticketIndex < quantity; ticketIndex++) {
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
    public TicketsToRemove excludeTicketsFromShoppingcart(Long eventId) {
        var user = userService.getUserByJWTToken();
       var deleteList =  user.getShoppingCart().getTickets().stream()
                .filter(ticket -> ticket.getTicketStatus().equals(TicketStatus.RESERVED) && ticket.getEvent().getId().equals(eventId)).collect(Collectors.toList());
        deleteList.forEach(ticket -> {
            ticket.setShoppingCart(null);
            ticket.setTicketStatus(TicketStatus.NOTSELLED);
        });
        var ticketsToRemove = new TicketsToRemove();
        ticketsToRemove.setEventId(1L);
        return ticketsToRemove;
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

    public List<Ticket> removeFromShoppingCart(Long ticketId) {
        User user = userService.getUserByJWTToken();
        Ticket ticket = ticketRepository.findById(ticketId).get();
        List<Ticket> ticketList = user.getShoppingCart().getTickets();
        ticketList.removeIf(currentTicket -> currentTicket.getId().equals(ticket.getId()));
        user.getShoppingCart().setTickets(ticketList);

        userService.updateUser(user);

        return ticketList;
    }
}
