package org.bs.gateway.filter;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.reactor.context.SaReactorHolder;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.bs.api.model.LoginUser;
import org.bs.common.core.constant.TokenConstant;
import org.bs.common.core.enums.ResultCodeEnum;
import org.bs.common.core.utils.ServletUtils;
import org.bs.common.core.utils.StringUtil;
import org.bs.gateway.config.IgnoreWhiteProperties;
import org.bs.satoken.service.TokenRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;

/**
 * @author :wkh
 */
@Configuration
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Autowired
    private TokenRefreshService tokenRefreshService;


    /**
     * 注册 Sa-Token 全局过滤器
     */
//    @Bean
//    public SaReactorFilter getSaReactorFilter(IgnoreWhiteProperties ignoreWhite) {
//        return new SaReactorFilter()
//                // 拦截地址
//                .addInclude("/**")
//                .addExclude("/favicon.ico", "/actuator/**")
//                // 鉴权方法：每次访问进入
//                .setAuth(obj -> {
//                    // 登录校验 -- 拦截所有路由
//                    SaRouter.match("/**")
//                            .notMatch(ignoreWhite.getWhites())
//                            .check(r -> {
//                                // 检查是否登录 是否有token
//                                StpUtil.checkLogin();
//                            });
//                }).setError(e -> {
//                    log.error(e.getMessage(), e);
//                    return e;
//                });
//    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 写入WebFilterChain对象
        exchange.getAttributes().put(SaReactorHolder.CHAIN_KEY, chain);
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getPath();
        // 跳过不需要验证的路径
        if (StringUtil.matches(url, ignoreWhite.getWhites())) {
            return chain.filter(exchange);
        }

        ServerHttpRequest.Builder mutate = request.mutate();

        // ---------- 全局认证处理
        try {
            // 写入全局上下文 (同步)
            SaReactorSyncHolder.setContext(exchange);
            // 调用satoken认证用户登录
            StpUtil.checkLogin();
            // 传递token
//            addHeader(mutate, StpUtil.getTokenName(), StpUtil.getTokenValue());

        } catch (NotLoginException exception) {
            // 判断token是否过期
            if (NotLoginException.TOKEN_TIMEOUT.equals(exception.getType())) {
                // 判断refresh token是否过期
                if (!tokenRefreshService.verifyRefreshToken(true)) {
                    throw exception;
                }

                HttpServletResponse response = (HttpServletResponse) exchange.getResponse();
                // TODO 网关传递给子应用时，需要将token重新设置到header中，否则子应用获取不到token
                // TODO 刷新token时 可正常访问，需要验证response是有网关返回还是又子应用返回 从而下发新的token
                // if refresh token is valid then re login and send new access token
                LoginUser loginUser = tokenRefreshService.reLogin();
                response.setHeader(TokenConstant.AUTHORIZATION, loginUser.getToken());
                response.setHeader(TokenConstant.AUTHORIZATION_REFRESH, String.valueOf(ResultCodeEnum.TOKEN_REFRESH.getCode()));
                return chain.filter(exchange);
            }

            log.error(exception.getMessage(), exception);
            throw exception;
        } finally {
            // 清除上下文
            SaReactorSyncHolder.clearContext();
        }

        // ---------- 执行

        // 写入全局上下文 (同步)
        SaReactorSyncHolder.setContext(exchange);

        // 执行
        return chain.filter(exchange).contextWrite(ctx -> {
            // 写入全局上下文 (异步)
            ctx = ctx.put(SaReactorHolder.CONTEXT_KEY, exchange);
            return ctx;
        }).doFinally(r -> {
            // 清除上下文
            SaReactorSyncHolder.clearContext();
        });


//        try {
//            // 调用satoken认证用户登录
//            StpUtil.checkLogin();
//            // 传递token
//            addHeader(mutate, StpUtil.getTokenName(), StpUtil.getTokenValue());
//        } catch (NotLoginException exception) {
//            // 判断token是否过期
//            if (NotLoginException.TOKEN_TIMEOUT.equals(exception.getType())) {
//                // 判断refresh token是否过期
//                if (!tokenRefreshService.verifyRefreshToken(true)) {
//                    throw exception;
//                }
//
//                HttpServletResponse response = (HttpServletResponse) exchange.getResponse();
//                // TODO 网关传递给子应用时，需要将token重新设置到header中，否则子应用获取不到token
//                // TODO 刷新token时 可正常访问，需要验证response是有网关返回还是又子应用返回 从而下发新的token
//                // if refresh token is valid then re login and send new access token
//                LoginUser loginUser = tokenRefreshService.reLogin();
//                response.setHeader(TokenConstant.AUTHORIZATION, loginUser.getToken());
//                response.setHeader(TokenConstant.AUTHORIZATION_REFRESH, String.valueOf(ResultCodeEnum.TOKEN_REFRESH.getCode()));
//                return chain.filter(exchange);
//            }
//
//            log.error(exception.getMessage(), exception);
//            throw exception;
//        }
//        return chain.filter(exchange);
    }

    /**
     * 添加请求头
     */
    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtils.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
