package at.commodussolutions.plentyentry.ordermanagement.event.dbInit;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import at.commodussolutions.plentyentry.ordermanagement.event.service.EventService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Builder
@Component
public class EventBuilder {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private Environment env;

    public void buildEvent() throws IOException {
        ArrayList<String> eventImageUrls1 = new ArrayList<>();
        ArrayList<String> eventImageUrls2 = new ArrayList<>();

        eventImageUrls1.add("https://eventimagesbucket.s3.us-east-2.amazonaws.com/eventtest/dummy/party1.jpg");
        eventImageUrls1.add("https://eventimagesbucket.s3.us-east-2.amazonaws.com/eventtest/dummy/party2.jpg");

        eventImageUrls2.add("https://eventimagesbucket.s3.us-east-2.amazonaws.com/eventtest/dummy/party2.jpg");
        eventImageUrls2.add("https://eventimagesbucket.s3.us-east-2.amazonaws.com/eventtest/dummy/party1.jpg");


        //EVENT NUMBER 1 -> Jogassen
        Event event = new Event();
        event.setName("Jogassn");
        event.setStartDateTime(LocalDateTime.now());
        event.setEndDateTime(LocalDateTime.now());
        event.setDescription("Für jeden Tiroler eine Saufparty!");
        event.setPrice(BigDecimal.valueOf(8.99));
        event.setTicketCounter(Long.parseLong("10"));
        event.setTicketId(1L);
        event.setAddress("Miau miau Cat Cat");
        event.setCity("Seini Hons");
        event.setEventImageUrls(eventImageUrls1);
        eventRepository.save(event);


        //EVENT NUMBER 2 -> Bourbon Street
        Event event2 = new Event();
        event2.setName("Bourbon Street");
        event2.setStartDateTime(LocalDateTime.now());
        event2.setEndDateTime(LocalDateTime.now());
        event2.setDescription("Für jeden Schicker eine Saufparty!");
        event2.setPrice(BigDecimal.valueOf(10.00));
        event2.setTicketCounter(Long.parseLong("4"));
        event2.setTicketId(5L);
        event2.setAddress("Schicker Blowis");
        event2.setCity("Fieberbrooklyn");
        event2.setEventImageUrls(eventImageUrls2);
        //Build Data
        eventRepository.save(event2);
        //eventService.createNewEvent(event2);
    }
}