package at.commodussolutions.plentyentry.user.userdata;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dbInit.UserInitializer;
import at.commodussolutions.plentyentry.user.userdata.dto.UserAuthReqDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
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

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@Transactional
public class UserRestServiceTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInitializer userInitializer;

    private final static String baseUrl = "/api/backend/user";


    @BeforeEach
    void createData() {
        if (userInitializer.shouldDataBeInitialized()) {
            userInitializer.initData();
        }
    }

    //Password must be set manually, because of the Ecrypt Reader he reads in non crypt format but writes in crypt so that was the problem
    @Test
    void userLoginTest() throws Exception {
        var defaultUser = userRepository.findAll().get(0);
        UserAuthReqDTO userAuthReqDTO = new UserAuthReqDTO();
        userAuthReqDTO.setEmail(defaultUser.getEmail());
        userAuthReqDTO.setPassword("password");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl + "/authenticate")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userAuthReqDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        UserDTO userDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), UserDTO.class);

        Assertions.assertEquals(defaultUser.getId(), userDTO.getId());
        Assertions.assertEquals(defaultUser.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(defaultUser.getPassword(), userDTO.getPassword());
        Assertions.assertEquals(defaultUser.getFirstName(), userDTO.getFirstName());
        Assertions.assertEquals(defaultUser.getLastName(), userDTO.getLastName());
        Assertions.assertEquals(defaultUser.getBirthday(), userDTO.getBirthday());
    }

    @Test
    void getUserByIdTest() throws Exception {

        var allUser = userRepository.findAll();
        var firstUser = userRepository.findById(allUser.get(0).getId()).orElse(null);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/" + firstUser.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<UserDTO>() {
                });

        //TODO: mehr Assertions bitte und oben die typreference brauchst du nicht beim user login test kannst du es dir anschauen solange es keine liste ist die wir
        //TODO: zurück kriegen dann reicht die einzelne DTO.class bsp:  UserDTO userDTOAfterAuth = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), UserDTO.class);
        Assertions.assertEquals(firstUser.getEmail(), result.getEmail());
    }

    @Test
    void createUserTest() throws Exception {

        //User Test 1
        User user = new User();
        user.setFirstName("Max");
        user.setLastName("Mustermann");
        user.setEmail("max@mustermann.com");
        user.setPassword("password");
        user.setStreet("Johnny Street 11");
        user.setPostCode("63380");
        user.setCity("Kufstein");
        user.setAge(20);
        user.setSvNumber(2134564789);
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

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl + "/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<UserDTO>() {
                });

        var newUserFromRepository = userRepository.findById(result.getId()).orElseThrow();

        //TODO: bitte möglichst viel asssertions aufbauen
        Assertions.assertEquals(result.getFirstName(), newUserFromRepository.getFirstName());
    }



}
