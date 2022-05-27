package at.commodussolutions.plentyentry.user.userdata.rest.impl;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.rest.UserSpecialPrivilegesRestService;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserSpecialPrivilegesRestServiceImpl implements UserSpecialPrivilegesRestService {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    @Override
    public List<UserDTO> getAllUser() {
        List<User> user = userService.getAllUser();
        return userMapper.mapToListDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.mapToDTO(userService.getUserById(id));
    }

    @Override
    public UserDTO updateUser(UserDTO updatedUser) {
        User user = userService.getUserById(updatedUser.getId());
        userMapper.mapToEntity(updatedUser, user);
        return userMapper.mapToDTO(userService.updateUser(user));
    }
}
