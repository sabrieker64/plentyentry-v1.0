package at.commodussolutions.plentyentry.user.shoppingcart.service;

import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart createNewShoppingCart();

    ShoppingCart getShoppingCartById();

    ShoppingCart updateShoppingCartById(ShoppingCart shoppingCart);
}
