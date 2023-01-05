package org.bs.common.core.global;

import lombok.extern.slf4j.Slf4j;
import org.bs.common.core.domain.AjaxResult;
import org.bs.common.core.domain.ObjectErrorResult;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * form表单校验拦截
 *
 * @author :wkh
 */
@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MethodArgumentNotValidExceptionAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AjaxResult handle(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectErrorResult> objectErrorModels = new ArrayList<>();
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            if (objectError instanceof FieldError) {
                FieldError fe = (FieldError) objectError;
                objectErrorModels.add(new ObjectErrorResult(fe.getField(), objectError.getDefaultMessage()));
            }
        }
        return AjaxResult.fail(objectErrorModels, HttpStatus.EXPECTATION_FAILED);
    }
}
