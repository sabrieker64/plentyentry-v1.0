package at.commodussolutions.plentyentry.user.userdata.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.mapper.TicketMapper;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.rest.UserRestService;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: @Eker
 */

@RestController
public class UserRestServiceImpl implements UserRestService {

    @Autowired
    private UserService service;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TicketMapper ticketMapper;

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

    @Override
    public UserDTO login(String username, String password) {
        return userMapper.mapToDTO(service.userLogin(username, password));
    }


    //USerService

    @Override
    public Integer getUserAge(Long id) {
        return service.getUserAge(id);
    }

    @Override
    public String getUserCity(Long id) {
        return service.getUserCity(id);
    }

    @Override
    public List<TicketDTO> getUserTickets(Long id) {
        return ticketMapper.mapToListDTO(service.getUserTickets(id));
    }
}
