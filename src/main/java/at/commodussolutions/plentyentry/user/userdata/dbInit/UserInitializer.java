package at.commodussolutions.plentyentry.user.userdata.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserInitializer implements InitializeDatabase {
    @Autowired
    private UserRepository userRepository;


    @Override
    public String initializeName() {
        return null;
    }

    @Override
    public String initializerName() {
        return null;
    }

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
