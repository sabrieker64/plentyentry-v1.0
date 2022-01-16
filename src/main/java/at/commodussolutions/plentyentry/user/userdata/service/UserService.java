package at.commodussolutions.plentyentry.user.userdata.service;

import at.commodussolutions.plentyentry.user.userdata.beans.User;

/**
 * Author: @Eker
 */

public interface UserService {

    User getUserById(Long id);

    User registerNewUser(User user);

    String confirmToken(String token);
}
