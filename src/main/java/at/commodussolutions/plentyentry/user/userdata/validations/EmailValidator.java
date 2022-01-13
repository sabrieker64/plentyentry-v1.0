package at.commodussolutions.plentyentry.user.userdata.validations;

import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;

@Configuration
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        //TODO: Regex Check Email
        return true;
    }
}
