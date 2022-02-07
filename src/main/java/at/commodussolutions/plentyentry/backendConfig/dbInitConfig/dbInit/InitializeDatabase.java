package at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit;

import org.springframework.core.Ordered;

public interface InitializeDatabase extends Ordered {

    String initializeName();

    String initializerName();

    boolean shouldDataBeInitialized();

    int getOrder();

    void initData();


}
