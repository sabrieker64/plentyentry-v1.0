package at.commodussolutions.plentyentry.user.userdata.listener;

import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import at.commodussolutions.plentyentry.user.shoppingcart.mapper.ShoppingCartMapper;
import at.commodussolutions.plentyentry.user.shoppingcart.repository.ShoppingCartRepository;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import at.commodussolutions.plentyentry.user.shoppingcart.service.impl.ShoppingCartServiceImpl;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.PostPersist;

public class UserListener {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @PostPersist
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void afterPresist(final User user){
        ShoppingCart shoppingCart = new ShoppingCart();
        //shoppingCart.setUser(user);
        ShoppingCart createdShoppingCart = shoppingCartService.createNewShoppingCart(shoppingCart);


        user.setShoppingCart(createdShoppingCart );
        userRepository.save(user);


    }

}
