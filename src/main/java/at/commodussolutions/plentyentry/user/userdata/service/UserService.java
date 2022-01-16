package at.commodussolutions.plentyentry.user.userdata.service;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.user.userdata.beans.User;

import java.util.List;

/**
 * Author: @Eker
 */

public interface UserService {

    User getUserById(Long id);

    User registerNewUser(User user);

    String confirmToken(String token);

    List<Ticket> getUserTickets(Long id);

    String getUserCity(Long id);

    Integer getUserAge(Long id);
}
