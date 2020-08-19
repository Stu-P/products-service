package stu.learning.service.products.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import stu.learning.service.products.events.IEvent;

@Service("kafka")
public class KafkaEventPublisher implements IEventPublisher {
    Logger logger = LoggerFactory.getLogger(KafkaEventPublisher.class);

    @Value("${product.events.kafka.topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, IEvent> publisher;

    @Override
    public void Publish(IEvent event) {
        try {
            Message<IEvent> message = MessageBuilder
                    .withPayload(event)
                    .setHeader(KafkaHeaders.TOPIC, topicName)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, event.getKey())
                    .build();

            publisher.send(message);

        } catch (Exception ex) {
            logger.error("event publish failure: {}: {}", ex.getMessage(), ex.getCause());
        }
    }
}
