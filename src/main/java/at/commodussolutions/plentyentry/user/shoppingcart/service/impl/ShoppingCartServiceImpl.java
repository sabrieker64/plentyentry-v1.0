package at.commodussolutions.plentyentry.user.shoppingcart.service.impl;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.shoppingcart.repository.ShoppingCartRepository;
import at.commodussolutions.plentyentry.user.shoppingcart.rest.ShoppingCartRestService;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
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

    @Override
    public ShoppingCart createNewShoppingCart(User user) {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        ShoppingCart shoppingCartReturn = shoppingCartRepository.save(new ShoppingCart());


        /*
        if(shoppingCart.getTickets().iterator().hasNext() && shoppingCart.getTickets()!=null) {
            while(shoppingCart.getTickets().iterator().hasNext()) {
                Ticket ticket = shoppingCart.getTickets().iterator().next();
                ticket.setShoppingCart(shoppingCartReturn);
                ticketRepository.save(ticket);
            }
        }

         */

        User userUpdateShoppingCart = userRepository.findByEmail(user.getEmail()).get();

        userUpdateShoppingCart.setShoppingCart(shoppingCartReturn);

        userRepository.save(userUpdateShoppingCart);

        return shoppingCartReturn;
    }

    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartRepository.getById(id);
    }

    @Override
    public ShoppingCart updateShoppingCartById(ShoppingCart shoppingCart) {



        return shoppingCartRepository.save(shoppingCart);
    }
}
