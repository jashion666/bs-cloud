package org.bs.common.core.constant;

/**
 * @author :wkh
 */
public class TokenConstant {

    public static final String AUTHORIZATION = "bs-cloud-token";

    public static final String AUTHORIZATION_REFRESH = "Token-Refresh";

    public static final String USER_TOKEN_PREFIX = "user_token_";

    public static final long MILLIS_SECOND = 1000;

    public static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    public static final long REFRESH_TOKEN_MINUTE = 60;

    /**
     * 网关传递的token 时长 5分钟
     */
    public static final long GATE_WAY_TOKEN_MINUTES = 5 * MILLIS_MINUTE;

    /**
     * 网关token key
     */
    public static final String GATE_WAY_TOKEN = "gate_way_token";

}
