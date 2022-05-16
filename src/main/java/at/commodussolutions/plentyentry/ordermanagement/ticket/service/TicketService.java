package at.commodussolutions.plentyentry.ordermanagement.ticket.service;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;

import java.util.List;
import java.util.Set;

public interface TicketService {

    List<Ticket> getAllTickets();

    List<Ticket> getBoughtTickets();

    Ticket getTicketById(Long id);

    Ticket updateTicketById(Ticket updatedTicket);

    Ticket createNewTicket(Ticket ticket);

    void putTicketsToShoppingCart(Set<Ticket> ticketSet);

    List<Ticket> getTicketByEventId(Long eventId, Integer quantity);
}
