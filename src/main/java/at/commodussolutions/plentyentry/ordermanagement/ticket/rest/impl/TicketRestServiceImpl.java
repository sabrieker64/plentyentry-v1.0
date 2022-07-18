package at.commodussolutions.plentyentry.ordermanagement.ticket.rest.impl;
/**
 * Author: @Mina
 */

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketsToRemove;
import at.commodussolutions.plentyentry.ordermanagement.ticket.mapper.TicketMapper;
import at.commodussolutions.plentyentry.ordermanagement.ticket.rest.TicketRestService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public List<TicketDTO> getBoughtTickets() {
        return ticketMapper.mapToListDTO(ticketService.getBoughtTickets());
    }


    @Override
    public TicketDTO getTicketById(Long id) {
        return ticketMapper.mapToDTO(ticketService.getTicketById(id));
    }

    @Override
    public TicketDTO updateTicketById(TicketDTO updatedTicket) {
        Ticket ticket = ticketService.getTicketById(updatedTicket.getId());
        ticketMapper.mapToEntity(updatedTicket, ticket);
        return ticketMapper.mapToDTO(ticketService.updateTicketById(ticket));
    }

    @Override
    public void updateBoughtTicketStatus(List<TicketDTO> ticketDTOList) {
        List<Ticket> tickets = new ArrayList<>();
        ticketMapper.mapToListEntity(ticketDTOList, tickets);
        ticketService.updateBoughtTickets(tickets);
    }

    @Override
    public TicketDTO createNewTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticketMapper.mapToEntity(ticketDTO, ticket);
        ticket = ticketService.createNewTicket(ticket);
        return ticketMapper.mapToDTO(ticket);
    }

    @Override
    public void putTicketsToShoppingCart(List<TicketDTO> ticketDTOSet) {
        var ticketList = new ArrayList<Ticket>();
        ticketMapper.mapToListEntity(ticketDTOSet, ticketList);
        ticketService.putTicketsToShoppingCart(ticketList);
    }

    @Override
    public List<TicketDTO> findTicketByEvent(Long eventId, Long quantity) {
        return ticketMapper.mapToListDTO(ticketService.getTicketByEventId(eventId, quantity));
    }

    @Override
    public List<TicketDTO> selectTicketsAndAddToCustomerShoppingCart(Long eventId, Long quantity) {
        return ticketMapper.mapToListDTO(ticketService.getTicketForEventAndAddToCartByQuantity(eventId, quantity));
    }

    @Override
    public TicketsToRemove deleteTicketsFromShoppingCart(TicketsToRemove tickets) {
      return ticketService.excludeTicketsFromShoppingcart(tickets.getEventId());
    }

    @Override
    public List<TicketDTO> removeFromShoppingCart(Long ticketId) {
        return ticketMapper.mapToListDTO(ticketService.removeFromShoppingCart(ticketId));
    }

    @Override
    public List<TicketDTO> countTicketThatAreAvailable(long eventId) {
        return ticketMapper.mapToListDTO(ticketService.findAllTicketsThatAreAvailable(eventId));
    }
}
