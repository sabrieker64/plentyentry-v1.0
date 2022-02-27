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

    //TODO: LOOK how we are loading the password of the users from database, maybe the crypt algorithm does not work fine
    @Test
    void userLoginTest() throws Exception {
        var defaultUser = userRepository.findAll().get(0);

        UserAuthReqDTO userAuthReqDTO = new UserAuthReqDTO();
        userAuthReqDTO.setEmail(defaultUser.getEmail());
        userAuthReqDTO.setPassword(defaultUser.getPassword());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl + "/authenticate")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userAuthReqDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
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
        //TODO
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

        Assertions.assertEquals(result.getFirstName(), newUserFromRepository.getFirstName());
    }



}
