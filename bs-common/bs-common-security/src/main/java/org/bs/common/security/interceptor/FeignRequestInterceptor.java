package org.bs.common.security.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.bs.common.core.constant.TokenConstant;
import org.bs.common.core.exception.ServiceException;
import org.bs.common.core.utils.GatewayTokenUtil;
import org.bs.common.core.utils.IpUtils;
import org.bs.common.core.utils.ServletUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * feign 请求拦截器
 *
 * @author wkh
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        if (httpServletRequest == null) {
            throw new ServiceException();
        }
        // 传递token给子服务
        String header = httpServletRequest.getHeader(TokenConstant.AUTHORIZATION);
        requestTemplate.header(TokenConstant.AUTHORIZATION, header);
        // 创建并传递gateway token给子服务验证
        requestTemplate.header(TokenConstant.GATE_WAY_TOKEN, GatewayTokenUtil.createToken());
        // 配置客户端IP
        requestTemplate.header("X-Forwarded-For", IpUtils.getIpAddr(ServletUtils.getRequest()));
    }
}
