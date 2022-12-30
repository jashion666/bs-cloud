package org.bs.gateway.config;

import lombok.Value;
import org.bs.gateway.handler.SentinelFallbackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :wkh
 */
@Configuration
public class GateWayConfig {

    @Bean
    public SentinelFallbackHandler sentinelFallbackHandler() {
        return new SentinelFallbackHandler();
    }
}
