package at.commodussolutions.plentyentry.ordermanagement.ticket.service;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    public List<Ticket> getAllTickets();
    public List<Ticket> getAllTicketsOfUser(User user);

    public Ticket getTicketById(Long id);

    public Ticket updateTicketById(Ticket updatedTicket);

    public Ticket createNewTicket(Ticket ticket);
}
