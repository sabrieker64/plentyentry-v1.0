package at.commodussolutions.plentyentry.ordermanagement.ticket.dbInit;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Builder
@Component
public class TicketBuilder {

    @Autowired
    private TicketRepository ticketRepository;

    public void buildTicket() {
        Ticket ticket1 = new Ticket();
        ticket1.setQuantity(2);
        ticket1.setTicketStatus(TicketStatus.NOTSELLED);
        ticket1.setEvent(null);
        ticket1.setUser(null);
        ticketRepository.save(ticket1);
    }

}
