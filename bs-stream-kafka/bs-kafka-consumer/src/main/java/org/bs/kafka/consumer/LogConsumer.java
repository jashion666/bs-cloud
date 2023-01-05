package org.bs.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author :wkh
 */
@Component
@Slf4j
public class LogConsumer {

    @Bean
    public Consumer<String> log() {
        return re -> {
            log.info(re + " ");
        };

    }
}
