package at.commodussolutions.plentyentry.user.userdata.dbInit;

import at.commodussolutions.plentyentry.backendConfig.security.PasswordEncoder;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Builder
@Component
public class UserBuilder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public void buildUser(){
        //User Test 1
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@doe.com");
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode("Test123!"));
        user.setStreet("Johnny Street 11");
        user.setPostCode("63380");
        user.setCity("Kufstein");
        user.setAge(20);
        user.setSvNumber(1234564789);
        user.setBirthday(LocalDate.now());
        user.setUserType(UserType.ADMIN);
        user.setUserGender(UserGender.MALE);
        user.setIsLoggedIn(true);
        user.setIsVerifiedAsEntertainer(true);
        user.setLocked(false);
        user.setEntertainedEvents(null);
        user.setPaymentMethod(null);
        user.setJwtToken(null);
        user.setEnabled(true);
        userRepository.save(user);

        //User Test 1
        user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johnny@doe.com");
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode("Test123!"));
        user.setStreet("Johnny Street 11");
        user.setPostCode("63380");
        user.setCity("Kufstein");
        user.setAge(20);
        user.setSvNumber(1314564789);
        user.setBirthday(LocalDate.now());
        user.setUserType(UserType.ADMIN);
        user.setUserGender(UserGender.MALE);
        user.setIsLoggedIn(true);
        user.setIsVerifiedAsEntertainer(true);
        user.setLocked(false);
        user.setEntertainedEvents(null);
        user.setPaymentMethod(null);
        user.setJwtToken(null);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
