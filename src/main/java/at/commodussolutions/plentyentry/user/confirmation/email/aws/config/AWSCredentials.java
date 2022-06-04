package at.commodussolutions.plentyentry.user.confirmation.email.aws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSCredentials {
    @Value("${amazonProperties.mailHost}")
    public String host;
    @Value("${amazonProperties.accessKeyMail}")
    public String accessKeyMail;
    @Value("${amazonProperties.secretKeyMail}")
    public String secretKeyMail;
}
