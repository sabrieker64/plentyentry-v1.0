package at.commodussolutions.plentyentry.ordermanagement.ticket.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TicketInitializer implements InitializeDatabase {
    @Override
    public String initializeName() {
        return null;
    }

    @Override
    public String initializerName() {
        return null;
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

        Event event = initEvent();
        User user = initUser();

        Ticket ticket1 = new Ticket();
        ticket1.setQuantity(1);
        ticket1.setTicketStatus(TicketStatus.EXPIRED);
        ticket1.setEvent(event);


    }

    public Event initEvent(){
        //EVENT NUMBER 1 -> Jogassen
        Event event = new Event();
        event.setName("Jogassn");
        event.setDate(LocalDate.now());
        event.setDescription("Für jeden Tiroler eine Saufparty!");
        event.setPrice(8.99);
        event.setTicketCounter(1);
        event.setTicketId(1L);
        event.setAddress("Miau miau Cat Cat");
        event.setCity("Seini Hons");

        return event;
    }

    public User initUser(){
        return null;
    }

}
