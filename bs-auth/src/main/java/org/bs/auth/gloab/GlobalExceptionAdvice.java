package org.bs.auth.gloab;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.bs.common.core.domain.AjaxResult;
import org.bs.common.core.enums.ResultCodeEnum;
import org.bs.common.i18n.config.NacosI18nMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常拦截
 *
 * @author :wkh
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @Autowired
    private NacosI18nMessageSource messageService;

    @ExceptionHandler(Exception.class)
    public AjaxResult exceptionHandler(Exception exception) {
        log.error(exception.getMessage(), exception);
        return AjaxResult.fail(messageService.getMessage("error.api"));
    }

    @ExceptionHandler(NotLoginException.class)
    public AjaxResult notLoginExceptionHandler(NotLoginException exception) {

        //  there is not exists in request header
        if (NotLoginException.NOT_TOKEN.equals(exception.getType())) {
            return AjaxResult.fail(messageService.getMessage("error.not.login"));
        }
        // token is invalid
        if (NotLoginException.INVALID_TOKEN.equals(exception.getType())) {
            return AjaxResult.fail(messageService.getMessage("error.invalid.token"));
        }
        // if token is expired
        if (NotLoginException.TOKEN_TIMEOUT.equals(exception.getType())) {
            return AjaxResult.fail(messageService.getMessage("error.login.timeout"), null, ResultCodeEnum.TOKEN_EXPIRE);
        }

        return AjaxResult.fail(messageService.getMessage("error.login.api"));
    }

}
