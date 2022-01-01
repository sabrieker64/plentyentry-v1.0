package at.commodussolutions.plentyentry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"at.commodussolutions.plentyentry"})
public class PlentyentryApplication {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        PlentyentryApplication.applicationContext = SpringApplication.run(PlentyentryApplication.class, args);
    }

}
