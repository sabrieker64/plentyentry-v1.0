package at.commodussolutions.plentyentry.user.confirmation.email.aws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceJavaMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class AWSMailConfig {

    @Autowired
    private AWSCredentials awsCredentials;


    @Bean
    public AmazonSimpleEmailService simpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsCredentials.accessKeyMail,
                                awsCredentials.secretKeyMail))).withRegion(Regions.US_EAST_2).build();
    }


    @Bean
    public JavaMailSender javaMailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceJavaMailSender(amazonSimpleEmailService);
    }

}
