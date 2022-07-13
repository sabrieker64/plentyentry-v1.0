package at.commodussolutions.plentyentry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"at.commodussolutions.plentyentry"})
@EntityScan(basePackages = {"at.commodussolutions.plentyentry"})
public class PlentyentryApplication {

    //private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(PlentyentryApplication.class, args);
    }

}
