package at.commodussolutions.plentyentry.user.userdata.service.impl;

import at.commodussolutions.plentyentry.backendConfig.security.PasswordEncoder;
import at.commodussolutions.plentyentry.backendConfig.utils.PlentyEntryBackendUtils;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.user.authentication.jwt.JwtTokenUtil;
import at.commodussolutions.plentyentry.user.confirmation.email.EmailSender;
import at.commodussolutions.plentyentry.user.confirmation.token.beans.ConfirmationToken;
import at.commodussolutions.plentyentry.user.confirmation.token.service.impl.ConfirmationTokenServiceImpl;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dto.UserAuthReqDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserLoginDTO;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import at.commodussolutions.plentyentry.user.userdata.validations.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * Author: @Eker
 */

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ConfirmationTokenServiceImpl confirmationTokenService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private Environment environment;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private PlentyEntryBackendUtils backendUtils;

    public UserServiceImpl(PlentyEntryBackendUtils backendUtils) {
        this.backendUtils = backendUtils;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User registerNewUser(User user) throws MessagingException {
        boolean isValidEmail = emailValidator.test(user.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email is not valid");
        }
        signUpUser(user);
        return user;
    }

    @Override
    public User resetPassword(User user) throws MessagingException {
        boolean userExists = this.userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {

            Random random = new Random();
            int randomNumber = random.nextInt(900) + 100;
            String newPassword = Integer.toString(randomNumber);
            newPassword = newPassword + "Neuanmeldung!";
            String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(newPassword);
            user.setPassword(encodedPassword);
            userRepository.save(user);

            String emailText = "Dein Passwort wurde auf " + newPassword + " geändert!";


            if (environment.acceptsProfiles(Profiles.of("test", "development"))) {
                emailSender.send(user.getEmail(), buildEmail(user.getLastName(), emailText));
            }
            emailSender.sendEmailFromSES(user.getEmail(), emailText, "Passwort wurde geändert!");


        } else {
            throw new IllegalStateException("Die E-Mail " + user.getEmail() + " is nicht im System vorhanden.");
        }

        return user;
    }

    private void signUpUser(User user) throws MessagingException {
        boolean userExists = this.userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Email wird schon verwendet");
        }
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        if (user.getUserType() == null) {
            user.setUserType(UserType.CUSTOMER);
        }

        userRepository.save(user);
        var shoppingCart = shoppingCartService.createNewShoppingCart();
        user.setShoppingCart(shoppingCart);
        userRepository.save(user);
        var token = createToken(user);
        //IF you are testing, you dont need the maildev server just open the confirmation link to enable the user !!Please do not change
        if (Objects.equals(Arrays.stream(this.environment.getActiveProfiles()).collect(Collectors.toList()).get(0), "test")) {
            log.info("The Profile is on test that means no Email would be send, just call the Confirmation Link to enable the user");
        } else {
            String link = this.backendUtils.getHost() + "api/backend/user/confirm?token=" + token.getToken();
            if (environment.acceptsProfiles(Profiles.of("test", "development"))) {
                emailSender.send(user.getEmail(), buildEmail(user.getLastName(), link));
            }
            emailSender.sendEmailFromSES(user.getEmail(), buildEmail(user.getLastName(), link), "Confirm your Email");
        }
    }


    public ConfirmationToken createToken(User user) {

        String uuidToken = UUID.randomUUID().toString();

        ConfirmationToken token = new ConfirmationToken();
        token.setToken(uuidToken);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        token.setUser(user);

        return confirmationTokenService.saveConfirmationToken(token);
    }


    private final static String USER_NOT_FOUND = "user with email %s not found";

    public User createJwtToken(UserAuthReqDTO userAuthReqDTO) throws Exception {
        String username = userAuthReqDTO.getEmail();
        String password = userAuthReqDTO.getPassword();
        userLogin(username, password);

        UserDetails userDetails = loadUserByUsername(userAuthReqDTO.getEmail());
        String newGeneratedToken = jwtTokenUtil.generateJwtToken(userDetails);
        User userWithToken = userRepository.getByEmail(username);
        userWithToken.setJwtToken(newGeneratedToken);
        User user;
        user = userWithToken;
        user.setJwtToken(newGeneratedToken);
        return user;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            return userRepository.findByEmail(email).get();
        } catch (Exception e) {
            log.info("User mit der E-Mail " + email + " konnte nicht geladen werden.");
            return null;
        }
        /*
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));

         */
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(UserLoginDTO userLoginDTO) throws Exception {
        User userFoundWithTypedEmail;
        userFoundWithTypedEmail = findUserByUsername(userLoginDTO.getEmail());
        String encodeTypedPasswordFromLogin = passwordEncoder.bCryptPasswordEncoder().encode(userLoginDTO.getPassword());
        if (!Objects.equals(encodeTypedPasswordFromLogin, userFoundWithTypedEmail.getPassword())) {
            throw new BadCredentialsException("Email oder Password ist falsch");
        }
        return userFoundWithTypedEmail;
    }

    @Override
    public User getUserByJWTToken() {
        if (servletRequest.getRequestURL().toString().contains("authenticate")) {
            return null;
        }

        if (!environment.acceptsProfiles(Profiles.of("test"))) {
            var jwtToken = servletRequest.getHeader(AUTHORIZATION);
            var username = jwtTokenUtil.getUsernameFromToken(jwtToken.replace("Bearer ", ""));
            return findUserByUsername(username);
        }
        return userRepository.findAll().stream().findFirst().orElseThrow();
    }

    @Override
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getByToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        enableUser(confirmationToken.getUser().getEmail());
            return "confirmed";
    }


    public User enableUser(String email) {
        var enabledUser = userRepository.getByEmail(email);
        enabledUser.setEnabled(true);
        return userRepository.save(enabledUser);
    }


    //user Service

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll(); //ticketRepository.findAllByShoppingCartId(loggedInUser);
    }

    @Override
    public List<Ticket> getUserTickets(Long id) {
        var loggedInUser = userRepository.getById(id).getShoppingCart();
        return null; //ticketRepository.findAllByShoppingCartId(loggedInUser);
    }

    @Override
    public String getUserCity(Long id) {
        var loggedInUser = userRepository.getById(id);
        return loggedInUser.getCity();
    }

    @Override
    public Integer getUserAge(Long id) {
        var loggedInUser = userRepository.getById(id);
        return loggedInUser.getAge();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.getByEmail(username);
    }

    private void userLogin(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID CREDENTIALS", e);
        }
    }


    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }



}
