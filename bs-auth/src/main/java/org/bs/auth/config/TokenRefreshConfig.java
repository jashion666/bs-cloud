package org.bs.auth.config;

import org.bs.common.core.constant.TokenConstant;
import org.bs.common.core.enums.ResultCodeEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :wkh
 */
@Configuration
public class TokenRefreshConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
                // 从网关获取header，判断token是否需要刷新
                String authorizationRefresh = request.getHeader(TokenConstant.AUTHORIZATION_REFRESH);
                if (String.valueOf(ResultCodeEnum.TOKEN_REFRESH.getCode()).equals(authorizationRefresh)) {
                    String token = request.getHeader(TokenConstant.AUTHORIZATION);
                    response.setHeader(TokenConstant.AUTHORIZATION, token);
                    response.setHeader(TokenConstant.AUTHORIZATION_REFRESH, authorizationRefresh);
                }
                return HandlerInterceptor.super.preHandle(request, response, handler);
            }
        }).addPathPatterns("/**");
    }

}
