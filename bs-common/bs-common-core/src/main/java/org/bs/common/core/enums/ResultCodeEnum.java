package org.bs.common.core.enums;

/**
 * http枚举
 *
 * @author :wkh
 */
public enum ResultCodeEnum {

    /**
     * api success code.
     */
    RESULT_SUCCESS_CODE(200),

    /**
     * api success code.
     */
    RESULT_WARNING_CODE(900),

    /**
     * api failed code.
     */
    RESULT_FAILED_CODE(500),
    /**
     * api failed code.
     */
    FORBIDDEN_CODE(403),
    /**
     * 没有权限
     */
    UNAUTHORIZED(401),
    /**
     * SESSION过期
     */
    TOKEN_EXPIRE(-9999),

    TOKEN_REFRESH(-3),

    /**
     * 验证码过期
     */
    VERIFY_EXPIRE_ERROR(-2);

    private final int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
