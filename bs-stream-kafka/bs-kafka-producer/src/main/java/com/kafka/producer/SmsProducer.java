package com.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 短信提供者
 *
 * @author :wkh
 */
@Component
public class SmsProducer {

    @Autowired
    private StreamBridge streamBridge;

    public void send(String msg) {
        streamBridge.send("sms-out-0", MessageBuilder.withPayload(msg).build());
    }
}
