package at.commodussolutions.plentyentry.user.userdata.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.mapper.TicketMapper;
import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.shoppingcart.repository.ShoppingCartRepository;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dto.UserAuthReqDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserLoginDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserRegisterDTO;
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
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.mapToDTO(userService.getUserById(id));
    }

    @Override
    public UserDTO createUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        userMapper.mapToEntityForRegister(userRegisterDTO, user);
        user = userService.registerNewUser(user);
        ShoppingCart newShoppingCart = shoppingCartService.createNewShoppingCart(user);
        user.setShoppingCart(newShoppingCart);
        return userMapper.mapToDTO(user);
    }

    @Override
    public String confirm(String token) {
        return userService.confirmToken(token);
    }

    @Override
    public UserDTO createJwtToken(UserAuthReqDTO userAuthReqDTO) throws Exception {
        return userMapper.mapToDTO(userService.createJwtToken(userAuthReqDTO));
    }

    @Override
    public UserDTO loadUserByJWTToken() {
        return userMapper.mapToDTO(userService.getUserByJWTToken());
    }

    @Override
    public UserDTO userLogin(UserLoginDTO userLoginDTO) throws Exception {
        return userMapper.mapToDTO(userService.loginUser(userLoginDTO));
    }


    //UserService

    @Override
    public Integer getUserAge(Long id) {
        return userService.getUserAge(id);
    }

    @Override
    public String getUserCity(Long id) {
        return userService.getUserCity(id);
    }

    @Override
    public List<TicketDTO> getUserTickets(Long id) {
        return ticketMapper.mapToListDTO(userService.getUserTickets(id));
    }

    @Override
    public UserDTO updateUser(UserDTO updatedUser) {
        User user = userService.getUserById(updatedUser.getId());
        return userMapper.mapToDTO(userService.updateUser(user));
    }
}
