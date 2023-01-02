package org.bs.satoken.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author :wkh
 */
@Data
@Component
@RefreshScope
public class SaTokenProperties {

    @Value("${sa-token.jwt-secret-key}")
    private String secret;

    @Value("${sa-token.name}")
    private String name;


    @Value("${sa-token.timeout}")
    private long timeout;

    @Value("${sa-token.rememberme.timeout}")
    private long rememberMeTimeout;

    @Value("${sa-token.activity-timeout}")
    private Integer activityTimeout;

    @Value("${sa-token.is-concurrent}")
    private boolean concurrent;

    @Value("${sa-token.is-share}")
    private boolean share;

    @Value("${sa-token.token-style}")
    private String tokenStyle;

    @Value("${sa-token.is-log}")
    private boolean log;

}
