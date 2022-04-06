package at.commodussolutions.plentyentry.user.shoppingcart.rest.impl;

import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import at.commodussolutions.plentyentry.user.shoppingcart.rest.ShoppingCartRestService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartRestServiceImpl implements ShoppingCartRestService {
    @Override
    public ShoppingCartDTO getShoppingCartById(Long id) {
        return null;
    }

    @Override
    public ShoppingCartDTO updateShoppingCartById(ShoppingCartDTO updatedShoppingCart) {
        return null;
    }

    @Override
    public ShoppingCartDTO createNewShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        return null;
    }
}
