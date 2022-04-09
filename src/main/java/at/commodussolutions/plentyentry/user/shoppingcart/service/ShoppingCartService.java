package at.commodussolutions.plentyentry.user.shoppingcart.service;

import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.userdata.beans.User;

public interface ShoppingCartService {
    ShoppingCart createNewShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart getShoppingCartById(Long id);

    ShoppingCart updateShoppingCartById(ShoppingCart shoppingCart);
}
