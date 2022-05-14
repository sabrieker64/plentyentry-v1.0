package at.commodussolutions.plentyentry.user.shoppingcart;

import at.commodussolutions.plentyentry.backendConfig.security.PasswordEncoder;
import at.commodussolutions.plentyentry.ordermanagement.event.dbInit.EventInitializer;
import at.commodussolutions.plentyentry.ordermanagement.event.mapper.EventMapper;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dbInit.TicketInitializer;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.mapper.TicketMapper;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.user.authentication.jwt.JwtTokenUtil;
import at.commodussolutions.plentyentry.user.shoppingcart.dbInit.ShoppingCartInitializer;
import at.commodussolutions.plentyentry.user.shoppingcart.mapper.ShoppingCartMapper;
import at.commodussolutions.plentyentry.user.shoppingcart.repository.ShoppingCartRepository;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dbInit.UserInitializer;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    JwtTokenUtil jwtTokenUtil;

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

        var jwt = jwtTokenUtil.generateJwtToken(testUser);

        testUser.setJwtToken(jwt);

        Set<TicketDTO> listTicket = new HashSet<>();
        listTicket.add(ticketMapper.mapToDTO(testTicket));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/api/backend/ticket/addToShoppingCart")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(listTicket))
                        .header("authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var updatedUser = userRepository.getByEmail(user.getEmail());
    }
}
