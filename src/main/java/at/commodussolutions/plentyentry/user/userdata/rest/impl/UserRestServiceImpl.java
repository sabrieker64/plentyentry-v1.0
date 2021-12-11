package at.commodussolutions.plentyentry.user.userdata.rest.impl;

import at.commodussolutions.plentyentry.user.userdata.rest.UserRestService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: @Eker
 */

@RestController
public class UserRestServiceImpl implements UserRestService {
    @Override
    public String getHello() {
        return "Hi Guys, Commodus Solutions first rest call";
    }
}
