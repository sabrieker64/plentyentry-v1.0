package at.commodussolutions.plentyentry.ordermanagement.event.service.impl;
/**
 * Author: @Mina
 */


import at.commodussolutions.plentyentry.ordermanagement.event.aws.dto.AWSEventImagesUploadDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.aws.model.Base64DecodedMultipartFile;
import at.commodussolutions.plentyentry.ordermanagement.event.aws.rest.impl.AwsBucketRestServiceImpl;
import at.commodussolutions.plentyentry.ordermanagement.event.aws.service.AmazonClient;
import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import at.commodussolutions.plentyentry.ordermanagement.event.service.EventService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    private AwsBucketRestServiceImpl awsBucketRestService;


    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllMaintainedEvents() {

        User user = userService.getUserByJWTToken();

        if (user.getUserType().equals(UserType.MAINTAINER) || user.getUserType().equals(UserType.SUPERADMIN) || user.getUserType().equals(UserType.ADMIN)) {
            Set<Event> maintainedEvents = user.getEntertainedEvents();
            return new ArrayList<>(maintainedEvents);
        }

        throw new NotFoundException("Sie haben keine Veranstaltungen");

    }

    @Override
    public Event createNewEvent(Event event) throws IOException {

        AWSEventImagesUploadDTO awsEventData = new AWSEventImagesUploadDTO();

        awsEventData.setEventName(event.getName());
        awsEventData.setUsername(userService.getUserByJWTToken().getEmail());

        List<MultipartFile> list = new ArrayList<>();

        for (String base64Url : event.getEventImageUrls()) {
            String base64Image = base64Url.split(",")[1];
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            MultipartFile file = new Base64DecodedMultipartFile(imageBytes);
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
            list.add(file);
        }

        List<String> imgLinks = awsBucketRestService.uploadFiles(list, awsEventData);
        event.setEventImageUrls(imgLinks);

        Event createdEvent = eventRepository.save(event);

        User currentUser = userService.getUserByJWTToken();

        Set<Event> entertainedEvents = currentUser.getEntertainedEvents();
        entertainedEvents.add(createdEvent);
        currentUser.setEntertainedEvents(entertainedEvents);
        userService.updateUser(currentUser);

        return createdEvent;
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.getById(id);
    }

    @Override
    public Event updateEventById(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Page<Event> getEventsByCriteria(String criteria) {
        return null;
    }

    @Override
    public Long countHowMuchTicketisLeft(Long eventId) {
        var event = eventRepository.getById(eventId);
        var allTickets = event.getTicketCounter();
        var alreadySolOrNotAvaillableAnymore =
                ticketService.findAllTicketsThatAreNotAvailableAnymore(eventId).stream().count();
        Long.parseLong(String.valueOf(alreadySolOrNotAvaillableAnymore));


        // todo ein eigenes object DTO für die ShoppingCart die von einer listen von tickets beschmückt ist und die
        //  quiantity hat und den preis
        return null;
    }
}
