package at.commodussolutions.plentyentry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PlentyentryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlentyentryApplication.class, args);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello";
    }

}
