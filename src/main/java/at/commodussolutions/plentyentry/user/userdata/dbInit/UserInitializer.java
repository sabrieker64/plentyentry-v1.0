package at.commodussolutions.plentyentry.user.userdata.dbInit;

import at.commodussolutions.plentyentry.backendConfig.dbInitConfig.dbInit.InitializeDatabase;
import at.commodussolutions.plentyentry.backendConfig.security.PasswordEncoder;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserInitializer implements InitializeDatabase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBuilder userBuilder;


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
        return userRepository.count() == 0;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void initData() {
        userBuilder.buildUser();
    }

}
