package at.commodussolutions.plentyentry.ordermanagement.ticket.service;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketsToRemove;

import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets();

    List<Ticket> getBoughtTickets();

    Ticket getTicketById(Long id);

    Ticket updateTicketById(Ticket updatedTicket);

    Ticket createNewTicket(Ticket ticket);

    void putTicketsToShoppingCart(List<Ticket> ticketSet);

    List<Ticket> getTicketByEventId(Long eventId, Long quantity);

    void createAutomaticTicketsForNewEvent(Long eventId, Long quantity);

    List<Ticket> getTicketForEventAndAddToCartByQuantity(Long eventId, Long quantity);

    TicketsToRemove excludeTicketsFromShoppingcart(Long eventId);

    List<Ticket> findAllTicketsThatAreNotAvailableAnymore(Long eventId);

    void updateBoughtTickets(List<Ticket> tickets);

    List<Ticket> removeFromShoppingCart(Long ticketId);

    List<Ticket> findAllTicketsThatAreAvailable(long eventId);
}
