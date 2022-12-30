package org.bs.common.redis.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author :wkh
 */
@Data
@Component
@RefreshScope
public class RedisProperties {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.database}")
    private Integer database;

    @Value("${spring.redis.lettuce.pool.max-active}")
    private Integer maxActive;

    @Value("${spring.redis.lettuce.pool.max-wait}")
    private Duration maxWait;

    @Value("${spring.redis.lettuce.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.redis.lettuce.pool.min-idle}")
    private Integer minIdle;
}
