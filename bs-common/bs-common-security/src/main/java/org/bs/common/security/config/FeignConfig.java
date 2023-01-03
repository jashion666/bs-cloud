package org.bs.common.security.config;

import feign.RequestInterceptor;
import org.bs.common.security.interceptor.FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author :wkh
 */
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
