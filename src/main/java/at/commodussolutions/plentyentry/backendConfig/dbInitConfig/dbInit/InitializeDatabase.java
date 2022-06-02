package at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit;

import org.springframework.core.Ordered;

import java.io.IOException;

public interface InitializeDatabase extends Ordered {

    String initializeName();

    String initializerName();

    boolean shouldDataBeInitialized();

    int getOrder();

    void initData() throws IOException;


}
