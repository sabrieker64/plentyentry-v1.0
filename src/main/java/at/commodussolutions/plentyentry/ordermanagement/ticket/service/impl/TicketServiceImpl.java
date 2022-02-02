package at.commodussolutions.plentyentry.ordermanagement.ticket.service.impl;
/**
 * Author: @Mina
 */

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getAllTicketsOfUser(User user) {
        return ticketRepository.findAllByUser(user);
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
}
