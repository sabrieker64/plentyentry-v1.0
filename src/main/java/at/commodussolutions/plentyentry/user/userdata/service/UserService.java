package at.commodussolutions.plentyentry.user.userdata.service;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Author: @Eker
 */

public interface UserService extends UserDetailsService {

    User getUserById(Long id);

    User registerNewUser(User user);

    String confirmToken(String token);

    List<Ticket> getUserTickets(Long id);

    String getUserCity(Long id);

    Integer getUserAge(Long id);

    User findUserByUsername(String username);


    User createJwtToken(User user) throws Exception;

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
