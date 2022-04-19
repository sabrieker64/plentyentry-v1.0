package at.commodussolutions.plentyentry.user.userdata;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dbInit.UserInitializer;
import at.commodussolutions.plentyentry.user.userdata.predicate.UserPredicatesBuilder;
import at.commodussolutions.plentyentry.user.userdata.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@Transactional
public class JPAQuerydslIntegrationTest {
    @Autowired
    private UserRepository repo;

    @Autowired
    private UserInitializer userInitializer;

    @BeforeEach
    void createData() {
        if (userInitializer.shouldDataBeInitialized()) {
            userInitializer.initData();
        }
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        UserPredicatesBuilder builder = new UserPredicatesBuilder().with("lastName", ":", "Doe");

        Iterable<User> results = repo.findAll(builder.build());

        System.out.println("TEST");
        //assertThat(results, containsInAnyOrder(userJohn, userTom));
    }
}
