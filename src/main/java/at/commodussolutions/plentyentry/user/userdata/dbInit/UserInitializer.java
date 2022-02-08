package at.commodussolutions.plentyentry.user.userdata.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
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
        return "User Group";
    }

    @Override
    public String initializerName() {
        return "User Initializer";
    }

    @Override
    public boolean shouldDataBeInitialized() {
        return true;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void initData() {
        //User Test 1
        User user = new User();
        user.setEnabled(true);
        user.setEmail("test@hotmail.com");
        user.setPassword("password");
        user.setFirstName("DummyFirstName");
        user.setLastName("DummyLastName");
        userRepository.save(user);
    }
}
