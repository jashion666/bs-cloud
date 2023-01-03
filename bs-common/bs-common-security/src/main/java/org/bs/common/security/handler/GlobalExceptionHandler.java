package org.bs.common.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.bs.common.core.domain.AjaxResult;
import org.bs.common.core.exception.NotFromGatewayException;
import org.bs.common.i18n.config.NacosI18nMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author wkh
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private NacosI18nMessageSource messageSource;

    /**
     * 内部服务调用异常
     */
    @ExceptionHandler(NotFromGatewayException.class)
    public AjaxResult handleNotFromGatewayException(NotFromGatewayException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("未通过gateway访问，地址{}", requestURI);
        return AjaxResult.fail(messageSource.getMessage("error.gateway.access"));
    }
}
