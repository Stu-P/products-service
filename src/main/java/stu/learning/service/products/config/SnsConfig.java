package stu.learning.service.products.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.SnsClientBuilder;
import java.net.URI;

@Configuration
public class SnsConfig {
    @Value("${sns.endpoint:}")
    String snsEndpoint;

    @Bean
    public SnsClient snsClient() {
        SnsClientBuilder builder = SnsClient.builder();
        if(!snsEndpoint.isEmpty()) {
            builder.endpointOverride(URI.create(snsEndpoint));
        }
        return builder.build();
    }
}