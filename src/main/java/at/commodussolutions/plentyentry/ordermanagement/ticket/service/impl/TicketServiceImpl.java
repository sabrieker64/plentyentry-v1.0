package at.commodussolutions.plentyentry.ordermanagement.ticket.service.impl;
/**
 * Author: @Mina
 */

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<Ticket> putTicketsToShoppingCart(Set<Ticket> ticketSet) {

        List<Ticket> ticketList = new ArrayList<>();

        while(ticketSet.iterator().hasNext() && !ticketSet.isEmpty() && ticketSet != null) {
            Ticket tempTicket = ticketSet.iterator().next();
            ticketList.add(ticketRepository.save(ticketRepository.getById(tempTicket.getId())));
        }

        return ticketList;


    }
}
