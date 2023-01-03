package org.bs.common.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bs.common.core.enums.ResultCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.List;

/**
 * @author :wkh
 */

public class AjaxResult extends ResponseEntity implements Serializable {

    private static final long serialVersionUID = 7130801760271010160L;

    @SuppressWarnings("unchecked")
    public AjaxResult(@Nullable Object body, HttpStatus status) {
        super(body, null, status);
    }

    @SuppressWarnings("unchecked")
    public AjaxResult(MultiValueMap<String, String> headers, HttpStatus status) {
        super(null, headers, status);
    }

    @SuppressWarnings("unchecked")
    public AjaxResult(@Nullable Object body, @Nullable MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public static AjaxResult success() {
        return new AjaxResult(Body.builder().code(ResultCodeEnum.RESULT_SUCCESS_CODE.getCode()).msg("").build(), HttpStatus.OK);
    }

    public static AjaxResult success(String message) {
        return new AjaxResult(Body.builder().code(ResultCodeEnum.RESULT_SUCCESS_CODE.getCode()).msg(message).build(), HttpStatus.OK);
    }

    public static AjaxResult success(String message, Object data) {
        return new AjaxResult(Body.builder().code(ResultCodeEnum.RESULT_SUCCESS_CODE.getCode()).msg(message).data(data).build(), HttpStatus.OK);
    }

    public static AjaxResult success(String message, MultiValueMap<String, String> headers, Object data) {
        new ResponseEntity<>("", headers, HttpStatus.OK);
        return new AjaxResult(Body.builder().code(ResultCodeEnum.RESULT_SUCCESS_CODE.getCode()).msg(message).data(data).build(), headers, HttpStatus.OK);
    }

    public static AjaxResult success(Object data) {
        return new AjaxResult(Body.builder().code(ResultCodeEnum.RESULT_SUCCESS_CODE.getCode()).data(data).build(), HttpStatus.OK);
    }

    public static AjaxResult fail(String message) {
        return new AjaxResult(Body.builder().msg(message).code(ResultCodeEnum.RESULT_FAILED_CODE.getCode()).build(), HttpStatus.OK);
    }

    public static AjaxResult fail(List<String> messageList) {
        return new AjaxResult(Body.builder().msgList(messageList).code(ResultCodeEnum.RESULT_FAILED_CODE.getCode()).build(), HttpStatus.OK);
    }

    public static AjaxResult fail(String message, Object data) {
        return new AjaxResult(Body.builder().msg(message).data(data).code(ResultCodeEnum.RESULT_FAILED_CODE.getCode()).build(), HttpStatus.OK);
    }

    public static AjaxResult fail(String message, Object data, HttpStatus httpStatus) {
        return new AjaxResult(Body.builder().msg(message).data(data).code(ResultCodeEnum.RESULT_FAILED_CODE.getCode()).build(), httpStatus);
    }

    public static AjaxResult fail(Object data, HttpStatus httpStatus) {
        return new AjaxResult(Body.builder().data(data).code(ResultCodeEnum.RESULT_FAILED_CODE.getCode()).build(), httpStatus);
    }

    public static AjaxResult fail(String message, Object data, ResultCodeEnum code) {
        return new AjaxResult(Body.builder().data(data).msg(message).code(code.getCode()).build(), HttpStatus.OK);
    }

    public Body getData() {
        return (Body) getBody();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Body {

        private int code;
        private String msg = "success";
        private List<String> msgList;
        private Object data;
    }

}
