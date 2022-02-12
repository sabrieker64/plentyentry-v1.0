package at.commodussolutions.plentyentry.ordermanagement.event.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventInitializer implements InitializeDatabase {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public String initializeName() {
        return "Event Group";
    }

    @Override
    public String initializerName() {
        return "Event Initializer";
    }

    @Override
    public boolean shouldDataBeInitialized() {
        return eventRepository.count() == 0;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void initData() {

        ArrayList<String> eventImageUrls = new ArrayList<>();

        eventImageUrls.add("abc");

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
        event.setEventImageUrls(eventImageUrls);

        //Build Data
        eventRepository.save(event);

        //EVENT NUMBER 2 -> Bourbon Street
        Event event2 = new Event();
        event2.setName("Bourbon Street");
        event2.setDate(LocalDate.now());
        event2.setDescription("Für jeden Schicker eine Saufparty!");
        event2.setPrice(10.00);
        event2.setTicketCounter(4);
        event2.setTicketId(5L);
        event2.setAddress("Schicker Blowis");
        event2.setCity("Fieberbrooklyn");
        event2.setEventImageUrls(eventImageUrls);
        //Build Data
        eventRepository.save(event2);
    }
}
