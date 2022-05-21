package at.commodussolutions.plentyentry.ordermanagement.ticket.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
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
        return false;
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
