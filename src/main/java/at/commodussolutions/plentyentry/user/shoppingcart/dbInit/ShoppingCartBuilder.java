package at.commodussolutions.plentyentry.user.shoppingcart.dbInit;

import at.commodussolutions.plentyentry.user.shoppingcart.repository.ShoppingCartRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Builder
@Component
public class ShoppingCartBuilder {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void buildShoppingCart() {
        at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart shoppingCart = new at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart();
        shoppingCart.setUser(null);
        shoppingCart.setTickets(null);
        shoppingCartRepository.save(shoppingCart);
    }
}
