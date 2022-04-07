package at.commodussolutions.plentyentry.ordermanagement.ticket.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.backendConfig.security.PasswordEncoder;
import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.event.repository.EventRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TicketInitializer implements InitializeDatabase {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EventRepository eventRepository;

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

        Event event = initEvent();
        User user = initUser();

        Ticket ticket1 = new Ticket();
        ticket1.setQuantity(2);
        ticket1.setTicketStatus(TicketStatus.NOTSELLED);
        ticket1.setEvent(event);
        ticket1.setUser(user);
        ticketRepository.save(ticket1);


    }

    public Event initEvent(){
        ArrayList<String> eventImageUrls = new ArrayList<>();

        eventImageUrls.add("abc");

        //EVENT NUMBER 1 -> Jogassen
        Event event = new Event();
        event.setName("Jogassn NEU");
        event.setDate(LocalDate.now());
        event.setDescription("Für jeden Tiroler eine Saufparty!");
        event.setPrice(8.99);
        event.setTicketCounter(100);
        event.setTicketId(2L);
        event.setAddress("Miau miau Cat Cat");
        event.setCity("Seini Hons");
        event.setEventImageUrls(eventImageUrls);
        //Build Data
        eventRepository.save(event);

        return eventRepository.findAll().get(0);
    }

    public User initUser(){
        //User Test 1
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("jogassntest@neu.com");
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode("password"));
        user.setStreet("Johnny Street 11");
        user.setPostCode("63380");
        user.setCity("Kufstein");
        user.setAge(20);
        user.setSvNumber(214564789);
        user.setBirthday(LocalDate.now());
        user.setUserType(UserType.ADMIN);
        user.setUserGender(UserGender.MALE);
        user.setIsLoggedIn(true);
        user.setIsVerifiedAsEntertainer(true);
        user.setLocked(false);
        user.setEntertainedEvents(null);
        user.setTickets(null);
        user.setPaymentMethod(null);
        user.setJwtToken(null);
        user.setEnabled(true);
        userRepository.save(user);

        return userRepository.findAll().get(0);
    }

}
