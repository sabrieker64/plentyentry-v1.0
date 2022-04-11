package at.commodussolutions.plentyentry.user.shoppingcart.service.impl;

import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.shoppingcart.repository.ShoppingCartRepository;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import com.amazonaws.services.fms.model.InvalidOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Override
    public ShoppingCart createNewShoppingCart(ShoppingCart shoppingCart, User user) {
        shoppingCart.setUser(user);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        var foundedShoppingCart = shoppingCartRepository.getById(id);
        var userForShoppingCartOnDb = foundedShoppingCart.getUser();
        var userBehindTheReq = userService.getUserByJWTToken();
        if (userForShoppingCartOnDb.equals(userBehindTheReq)) {
            return foundedShoppingCart;
        } else {
            throw new InvalidOperationException("Das angeforderte Warenkorb gehört nicht ihnen");
        }
    }

    @Override
    public ShoppingCart updateShoppingCartById(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }
}
