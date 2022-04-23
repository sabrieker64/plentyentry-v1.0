package at.commodussolutions.plentyentry.ordermanagement.ticket.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.backendConfig.security.PasswordEncoder;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketInitializer implements InitializeDatabase {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketBuilder ticketBuilder;

    @Override
    public String initializeName() {
        return "Ticket Group";
    }

    @Override
    public String initializerName() {
        return "Ticket Initializer";
    }

    @Override
    public boolean shouldDataBeInitialized() {
        return ticketRepository.count() == 0;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void initData() {
        ticketBuilder.buildTicket();
    }
}
