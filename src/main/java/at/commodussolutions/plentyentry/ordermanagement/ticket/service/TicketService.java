package at.commodussolutions.plentyentry.ordermanagement.ticket.service;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets();

    Ticket getTicketById(Long id);

    Ticket updateTicketById(Ticket updatedTicket);

    Ticket createNewTicket(Ticket ticket);
}
