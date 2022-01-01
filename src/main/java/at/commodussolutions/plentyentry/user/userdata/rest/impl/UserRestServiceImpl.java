package at.commodussolutions.plentyentry.user.userdata.rest.impl;

import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.rest.UserRestService;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: @Eker
 */

@RestController
public class UserRestServiceImpl implements UserRestService {

    @Autowired
    UserService service;
    @Autowired
    UserMapper userMapper;

    @Override
    public String getHello() {
        return "Hi Guys, Commodus Solutions first rest call";
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.mapToDTO(service.getUserById(id));
    }
}
