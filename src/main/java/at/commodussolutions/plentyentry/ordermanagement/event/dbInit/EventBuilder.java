package at.commodussolutions.plentyentry.ordermanagement.event.dbInit;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Builder
@Component
public class EventBuilder {

    private EventRepository eventRepository;

    public void buildEvent(){
        ArrayList<String> eventImageUrls1 = new ArrayList<>();
        ArrayList<String> eventImageUrls2 = new ArrayList<>();

        eventImageUrls1.add("https://eventimagesbucket.s3.us-east-2.amazonaws.com/eventtest/dummy/party1.jpg");
        eventImageUrls1.add("https://eventimagesbucket.s3.us-east-2.amazonaws.com/eventtest/dummy/party2.jpg");

        eventImageUrls2.add("https://eventimagesbucket.s3.us-east-2.amazonaws.com/eventtest/dummy/party2.jpg");
        eventImageUrls2.add("https://eventimagesbucket.s3.us-east-2.amazonaws.com/eventtest/dummy/party1.jpg");


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
        event.setEventImageUrls(eventImageUrls1);
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
        event2.setEventImageUrls(eventImageUrls2);
        //Build Data
        eventRepository.save(event2);
    }
}
