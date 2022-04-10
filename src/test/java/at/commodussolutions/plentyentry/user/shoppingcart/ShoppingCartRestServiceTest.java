package at.commodussolutions.plentyentry.user.shoppingcart;

import at.commodussolutions.plentyentry.backendConfig.security.PasswordEncoder;
import at.commodussolutions.plentyentry.ordermanagement.event.dbInit.EventInitializer;
import at.commodussolutions.plentyentry.ordermanagement.event.mapper.EventMapper;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dbInit.TicketInitializer;
import at.commodussolutions.plentyentry.ordermanagement.ticket.mapper.TicketMapper;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.user.shoppingcart.dbInit.ShoppingCartInitializer;
import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import at.commodussolutions.plentyentry.user.shoppingcart.mapper.ShoppingCartMapper;
import at.commodussolutions.plentyentry.user.shoppingcart.repository.ShoppingCartRepository;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dbInit.UserInitializer;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@Transactional
public class ShoppingCartRestServiceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private final static String baseUrl = "/api/backend/shoppingcart";

    @Autowired
    private TicketInitializer ticketInitializer;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private EventInitializer eventInitializer;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private UserInitializer userInitializer;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartInitializer shoppingCartInitializer;

    @BeforeEach
    void createData() {
        if (eventInitializer.shouldDataBeInitialized()) {
            eventInitializer.initData();
        }
        if (userInitializer.shouldDataBeInitialized()) {
            userInitializer.initData();
        }

        if (ticketInitializer.shouldDataBeInitialized()) {
            ticketInitializer.initData();
        }

        if (shoppingCartInitializer.shouldDataBeInitialized()) {
            shoppingCartInitializer.initData();
        }
    }

    @Test
    void getShoppingCartByID() throws Exception {

        updateShoppingCartByID();

        var firstShoppingCart = shoppingCartRepository.findAll().get(0);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl+"/"+firstShoppingCart.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ShoppingCartDTO resultShoppingCart = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<ShoppingCartDTO>() {
                });

        Assertions.assertEquals(firstShoppingCart.getUser().getEmail(), resultShoppingCart.getUser().getEmail());
    }

    @Test
    void updateShoppingCartByID() throws Exception {
        var firstShoppingCart = shoppingCartRepository.findAll().get(0);
        var firstTicket = ticketRepository.findAll().get(0);
        var firstUser = userRepository.findAll().get(0);

        Set<Ticket> ticketSet = new HashSet<>();
        ticketSet.add(firstTicket);

        firstShoppingCart.setUser(firstUser);
        firstShoppingCart.setTickets(ticketSet);


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(baseUrl)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstShoppingCart)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ShoppingCartDTO resultShoppingCart = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<ShoppingCartDTO>() {
                });

        Assertions.assertEquals(firstShoppingCart.getUser().getFirstName(), resultShoppingCart.getUser().getFirstName());
        Assertions.assertEquals(firstShoppingCart.getUser().getEmail(), resultShoppingCart.getUser().getEmail());

    }


    @Test
    void processTesting() throws Exception {
        User user = new User();
        user.setUserType(UserType.ADMIN);
        user.setFirstName("Testuser");
        user.setLastName("lastname");
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode("Test123!"));
        user.setEnabled(true);
        user.setUserGender(UserGender.MALE);
        user.setCity("KITZ");
        user.setPostCode("6360");
        user.setStreet("test strret");
        user.setBirthday(LocalDate.now());
        userService.registerNewUser(user);

        var testTicket = ticketRepository.findAll().get(0);

        var testUser = userRepository.findByEmail(user.getEmail()).orElseThrow();

        //todo da brauchen wir auch eine Business Logik, wenn der user einen ticket zu seinen shoppingcart hinzufügt
        //todo müssen wir dann auf das ticket die Shoppingcart ID setzen damit wir dann beim laden der shoppingcart die
        //todo ganzen tickets für den jweiligen shoppingcart laden können
        Set<Ticket> listTicket = new HashSet<>();
        testTicket.setShoppingCart(testUser.getShoppingCart());
        listTicket.add(testTicket);

        testUser.getShoppingCart().setTickets(listTicket);

        var foundedTickets = ticketRepository.findAllByShoppingCartId(testUser.getShoppingCart().getId());

        Assertions.assertEquals(foundedTickets.get(0), testTicket);
    }

    /*
    @Test
    void createShoppingCart() throws Exception {

        var shoppingCartDTO = new ShoppingCartDTO();
        var event = eventRepository.findAll().get(0);
        var user = userRepository.findByEmail("johnny@doe.com").get();
        var ticket = ticketRepository.findAll().get(0);

        ticket.setEvent(event);
        Set<TicketDTO> ticketSet = new HashSet<>();
        ticketSet.add(ticketMapper.mapToDTO(ticket));

        shoppingCartDTO.setTickets(ticketSet);
        shoppingCartDTO.setUser(userMapper.mapToDTO(user));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shoppingCartDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ShoppingCartDTO resultShoppingCart = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<ShoppingCartDTO>() {
                });

        Assertions.assertEquals(shoppingCartDTO.getUser().getEmail(), resultShoppingCart.getUser().getEmail());

    }
    
     */
}
