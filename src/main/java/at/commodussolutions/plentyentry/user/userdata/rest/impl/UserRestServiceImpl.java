package at.commodussolutions.plentyentry.user.userdata.rest.impl;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
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
    private UserService service;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.mapToDTO(service.getUserById(id));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        userMapper.mapToEntity(userDTO, user);
        user = service.registerNewUser(user);
        return userMapper.mapToDTO(user);
    }

    @Override
    public String confirm(String token) {
        return service.confirmToken(token);
    }
}
