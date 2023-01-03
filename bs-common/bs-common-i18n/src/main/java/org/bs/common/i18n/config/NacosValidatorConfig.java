package org.bs.common.i18n.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author :wkh.
 */
@Configuration
public class NacosValidatorConfig implements WebMvcConfigurer {

    @Autowired
    private NacosI18nMessageSource messageSource;

    @Bean
    public LocalInterceptor localInterceptor() {
        return new LocalInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor()).addPathPatterns("/**");
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    /**
     * 设置nacos i18n国际化message
     *
     * @return Validator
     */
    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);
        // 自定义MessageInterpolator 重写interpolate达到动态message效果
        validator.setMessageInterpolator(new NacosResourceBundleMessageInterpolator(messageSource));
        return validator;
    }
}
