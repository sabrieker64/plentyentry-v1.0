package at.commodussolutions.plentyentry.user.userdata;

import at.commodussolutions.plentyentry.user.coronastate.repository.CoronaStatusRepository;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dbInit.UserInitializer;
import at.commodussolutions.plentyentry.user.userdata.dto.UserAuthReqDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserRegisterDTO;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.impl.UserServiceImpl;
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
    private UserServiceImpl userService;

    @Autowired
    private UserInitializer userInitializer;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CoronaStatusRepository coronaStatusRepository;

    private final static String baseUrl = "/api/backend/user";


    @BeforeEach
    void createData() {
        if (userInitializer.shouldDataBeInitialized()) {
            userInitializer.initData();
        }
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

        UserDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), UserDTO.class);

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

        UserDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), UserDTO.class);

        User newUserFromRepository = userRepository.findById(result.getId()).orElseThrow();

        Assertions.assertEquals(result.getFirstName(), newUserFromRepository.getFirstName());
        Assertions.assertEquals(result.getLastName(), newUserFromRepository.getLastName());
        Assertions.assertEquals(result.getAge(), newUserFromRepository.getAge());
        Assertions.assertEquals(result.getBirthday(), newUserFromRepository.getBirthday());
        Assertions.assertEquals(result.getCity(), newUserFromRepository.getCity());
        Assertions.assertEquals(result.getId(), newUserFromRepository.getId());
        Assertions.assertEquals(result.getPostCode(), newUserFromRepository.getPostCode());
        Assertions.assertEquals(result.getShoppingCartDTO().getUser().getShoppingCartDTO(), newUserFromRepository.getShoppingCart().getId());


    }

    @Test
    public void confirmTest() throws Exception {

        String firstName="Super";
        String lastName="Mario";
        String email="super@mario.at";
        String password="password";
        String city="Kitzbichi";
        String postCode="6666";
        String street="Geht di nix uh";
        LocalDate birthday=LocalDate.now();
        UserGender userGender=UserGender.MALE;

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setFirstName(firstName);
        userRegisterDTO.setLastName(lastName);
        userRegisterDTO.setEmail(email);
        userRegisterDTO.setPassword(password);
        userRegisterDTO.setCity(city);
        userRegisterDTO.setPostCode(postCode);
        userRegisterDTO.setStreet(street);
        userRegisterDTO.setBirthday(birthday);
        userRegisterDTO.setUserGender(userGender);

        MvcResult mvcResultRegisteredUser = mvc.perform(MockMvcRequestBuilders.post(baseUrl +
                                "/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserDTO registeredUser = objectMapper.readValue(mvcResultRegisteredUser.getResponse().getContentAsString(StandardCharsets.UTF_8), UserDTO.class);

        Assertions.assertEquals(userRegisterDTO.getEmail(), registeredUser.getEmail());
        Assertions.assertEquals(userRegisterDTO.getFirstName(), registeredUser.getFirstName());
        Assertions.assertEquals(userRegisterDTO.getLastName(), registeredUser.getLastName());
        Assertions.assertEquals(userRegisterDTO.getBirthday(), registeredUser.getBirthday());

        User registeredUserEntity = new User();
        var token = userService.createToken(userMapper.mapToEntity(registeredUser,registeredUserEntity));
        assert registeredUserEntity != null;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl +
                                "/confirm?token=" + token.getToken())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var updatedUserToken = userRepository.findById(registeredUserEntity.getId()).orElse(null);
        Assertions.assertEquals(true, updatedUserToken.getEnabled());
    }

    @Test
    void userLoginTest() throws Exception {
        User defaultUser = userRepository.findAll().get(0);
        var token = userService.createToken(defaultUser);

        mvc.perform(MockMvcRequestBuilders.get(baseUrl +
                                "/confirm?token=" + token.getToken())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserAuthReqDTO userAuthReqDTO = new UserAuthReqDTO();
        userAuthReqDTO.setEmail(defaultUser.getEmail());
        userAuthReqDTO.setPassword("password");

        MvcResult mvcResultFinal = mvc.perform(MockMvcRequestBuilders.post(baseUrl + "/authenticate")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userAuthReqDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        UserDTO userDTO = objectMapper.readValue(mvcResultFinal.getResponse().getContentAsString(StandardCharsets.UTF_8),
                UserDTO.class);

        Assertions.assertEquals(defaultUser.getId(), userDTO.getId());
        Assertions.assertEquals(defaultUser.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(defaultUser.getPassword(), userDTO.getPassword());
        Assertions.assertEquals(defaultUser.getFirstName(), userDTO.getFirstName());
        Assertions.assertEquals(defaultUser.getLastName(), userDTO.getLastName());
        Assertions.assertEquals(defaultUser.getBirthday(), userDTO.getBirthday());
    }

    @Test
    public void getUserAgeTest() throws Exception {
        var allUser = userRepository.findAll();
        var firstUser = userRepository.findById(allUser.get(0).getId()).orElse(null);

        assert firstUser != null;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/service/getAge/" +firstUser.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Integer result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), Integer.class);

        Assertions.assertEquals(firstUser.getAge(), result);
    }

    @Test
    public void getUserCityTest() throws Exception {
        var allUser = userRepository.findAll();
        var firstUser = userRepository.findById(allUser.get(0).getId()).orElse(null);

        assert firstUser != null;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/service/getCity/" +firstUser.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertEquals(firstUser.getCity(), result);
    }

    @Test
    public void updateUserTest() throws Exception {
        var allUser = userRepository.findAll();
        var firstUser = userRepository.findById(allUser.get(0).getId()).orElse(null);

        assert firstUser != null;
        firstUser.setFirstName("Super");
        firstUser.setLastName("Mario");
        firstUser.setUserGender(UserGender.MALE);
        firstUser.setAge(22);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(baseUrl)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserDTO updatedUser = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), UserDTO.class);

        Assertions.assertEquals(firstUser.getFirstName(), updatedUser.getFirstName());
        Assertions.assertEquals(firstUser.getLastName(), updatedUser.getLastName());
        Assertions.assertEquals(firstUser.getUserGender(), updatedUser.getUserGender());
        Assertions.assertEquals(firstUser.getAge(), updatedUser.getAge());
    }
}
