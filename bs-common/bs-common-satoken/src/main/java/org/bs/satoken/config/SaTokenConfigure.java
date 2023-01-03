package org.bs.satoken.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

/**
 * @author :wkh
 */
@Configuration
@Order(1)
public class SaTokenConfigure {

    @Autowired
    private SaTokenProperties saTokenProperties;

    @Bean
    public StpLogic getStpLogicJwt() {
        return new AuthJwtForStateless();
    }

    /**
     * 配置satoken
     * @return SaTokenConfig
     */
    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName(saTokenProperties.getName());             // token名称 (同时也是cookie名称)
        config.setTimeout(saTokenProperties.getTimeout());       // token有效期，单位s 默认30天
        config.setActivityTimeout(saTokenProperties.getActivityTimeout());              // token临时有效期 (指定时间内无操作就视为token过期) 单位: 秒
        config.setIsConcurrent(saTokenProperties.isConcurrent());               // 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)
        config.setIsShare(saTokenProperties.isShare());                    // 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)
        config.setTokenStyle(saTokenProperties.getTokenStyle());               // token风格
        config.setIsLog(saTokenProperties.isLog());                     // 是否输出操作日志
        config.setJwtSecretKey(saTokenProperties.getSecret());
        return config;
    }

}

