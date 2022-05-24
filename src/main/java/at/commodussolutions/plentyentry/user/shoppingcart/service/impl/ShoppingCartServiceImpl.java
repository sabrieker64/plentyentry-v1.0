package at.commodussolutions.plentyentry.user.shoppingcart.service.impl;

import at.commodussolutions.plentyentry.ordermanagement.ticket.mapper.TicketMapper;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.shoppingcart.repository.ShoppingCartRepository;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
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
    private TicketMapper ticketMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Override
    public ShoppingCart createNewShoppingCart() {
        ShoppingCart shoppingCart1 = new ShoppingCart();
        return shoppingCartRepository.save(shoppingCart1);
    }

    @Override
    public ShoppingCart getShoppingCartById() {
        var user = userService.getUserByJWTToken();

        return user.getShoppingCart();

    }


    @Override
    public ShoppingCart updateShoppingCartById(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }
}
