package at.commodussolutions.plentyentry.user.shoppingcart.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShoppingCartInitializer implements InitializeDatabase {
    @Override
    public String initializeName() {
        return "ShoppingCart Group";
    }

    @Override
    public String initializerName() {
        return "ShoppingCart Initializer";
    }
//TODO METHODS IMPLEMENTING
    @Override
    public boolean shouldDataBeInitialized() {
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void initData() {

    }
}
