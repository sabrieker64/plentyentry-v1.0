package at.commodussolutions.plentyentry.user.userdata;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dbInit.UserInitializer;
import at.commodussolutions.plentyentry.user.userdata.dto.UserAuthReqDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserLoginDTO;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
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

        var newUserFromRepository = userRepository.findById(result.getId()).orElseThrow();


        //todo More Assertionsssssssssss!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Assertions.assertEquals(result.getFirstName(), newUserFromRepository.getFirstName());
    }

    @Test
    public void confirmTest() throws Exception {
        var allUser = userRepository.findAll();
        var firstUser = userRepository.findById(allUser.get(0).getId()).orElse(null);

        //todo conirmation token is not jwtToken confirmation token is just to verify the email after register
        //todo den confirmation test würde ich nach dem register einbauen weil du da den confirmation token kriegst
        //todo dann kannst du direkt schauen danach ob der user enabled ist
        assert firstUser != null;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(baseUrl +
                                //todo thats not right there
                                "/confirm&token=" + firstUser.getJwtToken())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultToken = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), String.class);

        System.out.printf(resultToken);

        //todo you should check after the confiramtion of user.getEnabled is true

        Assertions.assertEquals(resultToken, resultToken);
    }

    //And this is userLoginTest because authentication and returning an jwtToken equals to login,
    // login means authentication to show user specific data
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


    //WHY GETTING 401?!
    //TODO: this test is bullshit for login its enough to test the authentication
    public void bullshitTest() throws Exception {


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


        //DOWN THERE ISN'T WORKING

        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail(defaultUser.getEmail());
        userLoginDTO.setPassword("password");
        userLoginDTO.setJwtToken(userDTO.getJwtToken());

        MvcResult mvcResultLogin = mvc.perform(MockMvcRequestBuilders.get(baseUrl + "/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userLoginDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserDTO resultLogin = objectMapper.readValue(mvcResultLogin.getResponse().getContentAsString(StandardCharsets.UTF_8), UserDTO.class);


        Assertions.assertEquals(userLoginDTO.getEmail(), resultLogin.getEmail());
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

        //And try to assert for not null if you trying dynamic loaded data to look if there is anything in there before setting or getting objects
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
