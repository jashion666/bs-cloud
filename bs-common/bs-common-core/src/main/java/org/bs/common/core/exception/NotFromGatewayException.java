package org.bs.common.core.exception;

public final class NotFromGatewayException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private String detailMessage;

    public NotFromGatewayException() {
    }

    public NotFromGatewayException(String message) {
        this.message = message;
    }

    public NotFromGatewayException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public NotFromGatewayException setMessage(String message) {
        this.message = message;
        return this;
    }

    public NotFromGatewayException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
}
