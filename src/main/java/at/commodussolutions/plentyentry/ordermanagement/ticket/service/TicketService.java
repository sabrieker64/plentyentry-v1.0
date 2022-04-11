package at.commodussolutions.plentyentry.ordermanagement.ticket.service;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface TicketService {

    List<Ticket> getAllTickets();

    Ticket getTicketById(Long id);

    Ticket updateTicketById(Ticket updatedTicket);

    Ticket createNewTicket(Ticket ticket);

    void putTicketsToShoppingCart(Set<Ticket> ticketSet);
}
