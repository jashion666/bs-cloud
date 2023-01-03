package org.bs.gateway.handler;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.bs.common.core.domain.AjaxResult;
import org.bs.common.core.enums.ResultCodeEnum;
import org.bs.common.core.utils.ServletUtils;
import org.bs.common.i18n.config.NacosI18nMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author wkh
 */
@Order(-1)
@Configuration
@Slf4j
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    @Autowired
    private NacosI18nMessageSource messageSource;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        // 未登录
        if (ex instanceof NotLoginException) {
            NotLoginException notLoginException = (NotLoginException) ex;
            //  header中没有token 需要先登陆
            if (NotLoginException.NOT_TOKEN.equals(notLoginException.getType())) {
                return ServletUtils.webFluxResponseWriter(response, AjaxResult.fail(messageSource.getMessage("error.not.login")));
            }
            // token无效
            if (NotLoginException.INVALID_TOKEN.equals(notLoginException.getType())) {
                return ServletUtils.webFluxResponseWriter(response, AjaxResult.fail(messageSource.getMessage("error.invalid.token")));
            }
            // token过期
            if (NotLoginException.TOKEN_TIMEOUT.equals(notLoginException.getType())) {
                return ServletUtils.webFluxResponseWriter(response, AjaxResult.fail(messageSource.getMessage("error.login.timeout"), null, ResultCodeEnum.TOKEN_EXPIRE));
            }
            // 请求失败
            return ServletUtils.webFluxResponseWriter(response, AjaxResult.fail(messageSource.getMessage("error.api")));
        }

        log.error("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ex);
        // 404异常
        if (ex instanceof NotFoundException) {
            return ServletUtils.webFluxResponseWriter(response, AjaxResult.fail(messageSource.getMessage("error.not.found")));
        }
        // http异常
        if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            return ServletUtils.webFluxResponseWriter(response, AjaxResult.fail(responseStatusException.getMessage()));
        }

        // 500异常
        return ServletUtils.webFluxResponseWriter(response, AjaxResult.fail(messageSource.getMessage("error.system.error")));

    }
}
