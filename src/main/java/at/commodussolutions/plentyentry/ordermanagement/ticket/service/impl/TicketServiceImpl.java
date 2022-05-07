package at.commodussolutions.plentyentry.ordermanagement.ticket.service.impl;
/**
 * Author: @Mina
 */

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getBoughtTickets() {
        /* Set<Ticket> tempSet = user.getShoppingCart().getTickets();
        List<Ticket> tempList = new ArrayList<>(tempSet);
        List<Ticket> boughtTickets = new ArrayList<>();
        for(Ticket ticket : tempList) {
            if(ticket.getTicketStatus().equals(TicketStatus.SELLED)){
                boughtTickets.add(tempList.iterator().next());
            }
        }*/
        return userService.getUserByJWTToken().getShoppingCart().getTickets().stream()
                .filter(status -> status.getTicketStatus().equals(TicketStatus.SELLED)
                        || !status.getTicketStatus().equals(TicketStatus.EXPIRED))
                .collect(Collectors.toList());
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
    public void putTicketsToShoppingCart(Set<Ticket> ticketSet) {
        User user = userService.getUserByJWTToken();
        boolean addedAllTicketsToShoppingCart = user.getShoppingCart().getTickets().addAll(ticketSet);
        if (addedAllTicketsToShoppingCart) {
            log.info("Added to shoppingcart");
            userRepository.save(user);
        } else {
            log.error("Cant add tickets to shoppingcart");
        }
    }
}
