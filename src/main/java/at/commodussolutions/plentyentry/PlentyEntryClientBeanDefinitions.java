package at.commodussolutions.plentyentry;

import at.commodussolutions.plentyentry.backendConfig.utils.PESecuredValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PlentyEntryClientBeanDefinitions {


    @Bean
    public PESecuredValidator validator() {
        return new PESecuredValidator();
    }

}
