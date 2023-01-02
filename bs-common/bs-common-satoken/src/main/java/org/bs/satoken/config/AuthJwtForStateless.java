package org.bs.satoken.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.jwt.SaJwtUtil;
import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.jwt.error.SaJwtErrorCode;
import cn.dev33.satoken.jwt.exception.SaJwtException;
import org.bs.common.core.utils.StringUtil;

/**
 * @author :wkh
 */
public class AuthJwtForStateless extends StpLogicJwtForStateless {

    @Override
    public String getLoginIdNotHandle(String tokenValue) {
        if (StringUtil.isEmpty(tokenValue)) {
            return null;
        }
        try {
            Object loginId = SaJwtUtil.getLoginId(tokenValue, loginType, jwtSecretKey());
            return String.valueOf(loginId);
        } catch (SaJwtException e) {
            if (SaJwtErrorCode.CODE_30204 == e.getCode()) {
                return NotLoginException.TOKEN_TIMEOUT;
            }
            return null;
        }
    }

}
