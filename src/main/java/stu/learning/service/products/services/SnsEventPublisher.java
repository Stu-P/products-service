package stu.learning.service.products.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import stu.learning.service.products.events.IEvent;

@Service("sns")
public class SnsEventPublisher implements IEventPublisher {

    Logger logger = LoggerFactory.getLogger(SnsEventPublisher.class);

    private final SnsClient snsClient;
    private final ObjectMapper objectMapper;

    @Value("${product.events.topic}")
    private String topicArn;

    @Autowired
    public SnsEventPublisher(SnsClient snsClient, ObjectMapper objectMapper) {
        this.snsClient = snsClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void Publish(IEvent event) {
        try {
            var request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(objectMapper.writeValueAsString(event))
                    .build();

            var response = snsClient.publish(request);

            if (!response.sdkHttpResponse().isSuccessful()) {
                logger.error(String.format("Sns publish failed %s",response.sdkHttpResponse().statusText()));
            }

        } catch (Exception ex) {
            logger.error("Exception caught during sns publish: {} | {}", ex.getMessage(), ex.getCause());
        }
    }
}