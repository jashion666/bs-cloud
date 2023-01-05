//package com.kafka.producer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.stream.function.StreamBridge;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
///**
// * @author :wkh
// */
//@Component
//public class LogProducer {
//
//    @Autowired
//    private StreamBridge streamBridge;
//
//    public void send(String msg) {
//        streamBridge.send("log-out-0", MessageBuilder.withPayload(msg).build());
//    }
//}
