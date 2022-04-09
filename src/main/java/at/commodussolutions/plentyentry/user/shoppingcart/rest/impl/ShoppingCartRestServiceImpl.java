package at.commodussolutions.plentyentry.user.shoppingcart.rest.impl;


import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import at.commodussolutions.plentyentry.user.shoppingcart.mapper.ShoppingCartMapper;
import at.commodussolutions.plentyentry.user.shoppingcart.rest.ShoppingCartRestService;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartRestServiceImpl implements ShoppingCartRestService {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartDTO getShoppingCartById(Long id) {
        return shoppingCartMapper.mapToDTO(shoppingCartService.getShoppingCartById(id));
    }

    @Override
    public ShoppingCartDTO updateShoppingCartById(ShoppingCartDTO updatedShoppingCart) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(updatedShoppingCart.getId());
        return shoppingCartMapper.mapToDTO(shoppingCartService.updateShoppingCartById(shoppingCart));
    }

    @Override
    public ShoppingCartDTO createNewShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCartMapper.mapToEntity(shoppingCartDTO, shoppingCart);
        return shoppingCartMapper.mapToDTO(shoppingCartService.createNewShoppingCart(shoppingCart));
    }
}
