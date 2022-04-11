package at.commodussolutions.plentyentry.user.shoppingcart.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.user.shoppingcart.repository.ShoppingCartRepository;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingCartInitializer implements InitializeDatabase {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartBuilder shoppingCartBuilder;

    @Override
    public String initializeName() {
        return "ShoppingCart Group";
    }

    @Override
    public String initializerName() {
        return "ShoppingCart Initializer";
    }

    @Override
    public boolean shouldDataBeInitialized() {
        return shoppingCartRepository.count() ==0;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void initData() {
        shoppingCartBuilder.buildShoppingCart();
    }

}
