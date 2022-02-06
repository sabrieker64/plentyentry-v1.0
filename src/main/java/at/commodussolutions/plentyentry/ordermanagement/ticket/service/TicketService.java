package at.commodussolutions.plentyentry.ordermanagement.ticket.service;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    public List<Ticket> getAllTickets();

    public Ticket getTicketById(Long id);

    public Ticket updateTicketById(Ticket updatedTicket);

    public Ticket createNewTicket(Ticket ticket);
}
