package org.bs.satoken.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.bs.api.system.model.LoginUser;
import org.bs.satoken.service.ITokenService;
import org.springframework.stereotype.Service;

/**
 * @author :wkh
 */
@Service
public class TokenServiceImpl implements ITokenService {

    @Override
    public LoginUser getToken() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return LoginUser.builder()
                .userid(Integer.parseInt(tokenInfo.getLoginId().toString()))
                .token(tokenInfo.getTokenValue())
                .username(StpUtil.getExtra("username").toString())
                .rememberMe(Boolean.parseBoolean(StpUtil.getExtra("rememberMe").toString()))
                .tokenKey(tokenInfo.getTokenName()).build();
    }
}
