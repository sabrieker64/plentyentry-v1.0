package at.commodussolutions.plentyentry.ordermanagement.ticket.rest.impl;
/**
 * Author: @Mina
 */

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.mapper.TicketMapper;
import at.commodussolutions.plentyentry.ordermanagement.ticket.rest.TicketRestService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class TicketRestServiceImpl implements TicketRestService {

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Autowired
    TicketMapper ticketMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<TicketDTO> getAllTickets() {
        return ticketMapper.mapToListDTO(ticketService.getAllTickets());
    }


    @Override
    public TicketDTO getTicketById(Long id) {
        TicketDTO ticketDTO = ticketMapper.mapToDTO(ticketService.getTicketById(id));
        return ticketDTO;
    }

    @Override
    public TicketDTO updateTicketById(TicketDTO updatedTicket) {
        Ticket ticket = ticketService.getTicketById(updatedTicket.getId());
        return ticketMapper.mapToDTO(ticketService.updateTicketById(ticket));
    }

    @Override
    public TicketDTO createNewTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticketMapper.mapToEntity(ticketDTO,ticket);
        ticket = ticketService.createNewTicket(ticket);
        return ticketMapper.mapToDTO(ticket);
    }

    @Override
    public void putTicketsToShoppingCart(Set<TicketDTO> ticketDTOSet) {
        Set<Ticket> ticketSet = new HashSet<>();
        ticketService.putTicketsToShoppingCart(ticketMapper.mapToListEntity(ticketDTOSet, ticketSet));
    }
}
