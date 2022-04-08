package at.commodussolutions.plentyentry.user.shoppingcart.service;

import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public interface ShoppingCartService {

    ShoppingCart createNewShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart getShoppingCartById(Long id);

    ShoppingCart updateShoppingCartById(ShoppingCart shoppingCart);
}
