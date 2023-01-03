package org.bs.common.security.interceptor;

import org.bs.common.core.constant.TokenConstant;
import org.bs.common.core.exception.NotFromGatewayException;
import org.bs.common.core.utils.GatewayTokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :wkh
 */
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(TokenConstant.GATE_WAY_TOKEN);
        if (!GatewayTokenUtil.verify(token)) {
            throw new NotFromGatewayException();
        }
        return true;
    }
}
