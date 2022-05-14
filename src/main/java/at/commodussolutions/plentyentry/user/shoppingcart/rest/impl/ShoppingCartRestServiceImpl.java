package at.commodussolutions.plentyentry.user.shoppingcart.rest.impl;


import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import at.commodussolutions.plentyentry.user.shoppingcart.mapper.ShoppingCartMapper;
import at.commodussolutions.plentyentry.user.shoppingcart.rest.ShoppingCartRestService;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartRestServiceImpl implements ShoppingCartRestService {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartDTO getShoppingCartById() {
        return shoppingCartMapper.mapToDTO(shoppingCartService.getShoppingCartById());
    }

    @Override
    public ShoppingCartDTO updateShoppingCartById(ShoppingCartDTO updatedShoppingCart) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById();
        return shoppingCartMapper.mapToDTO(shoppingCartService.updateShoppingCartById(shoppingCart));
    }

}
