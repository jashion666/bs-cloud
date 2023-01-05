package org.bs.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author :wkh
 */
@Component
@Slf4j
public class SmsConsumer {
//    @KafkaListener(topics = {"test-request"})
    @Bean
    public Consumer<Message<?>> sms() {
        return message -> {
            Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
            if (acknowledgment != null) {
                log.info(message.getPayload().toString());
                acknowledgment.acknowledge();
            }
        };
    }
}
