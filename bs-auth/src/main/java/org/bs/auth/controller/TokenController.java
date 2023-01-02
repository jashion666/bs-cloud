package org.bs.auth.controller;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.bs.api.model.LoginUser;
import org.bs.common.core.constant.TokenConstant;
import org.bs.common.core.domain.AjaxResult;
import org.bs.common.core.exception.ServiceException;
import org.bs.common.i18n.config.NacosI18nMessageSource;
import org.bs.common.redis.RedisClient;
import org.bs.satoken.service.ITokenService;
import org.bs.satoken.service.TokenRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :wkh
 */
@RestController
@Slf4j
public class TokenController {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private TokenRefreshService tokenRefreshService;

    @Autowired
    private NacosI18nMessageSource messageSource;

    /**
     * 认证token
     *
     * @return json
     */
    @PostMapping("/authentication")
    public AjaxResult authentication() {

        try {
//            LoginUser loginUser = loginService.login(loginBody);
            LoginUser loginUser = new LoginUser();
            loginUser.setUsername("admin");
            loginUser.setRememberMe(false);
            loginUser.setUserid(1);
            StpUtil.login(loginUser.getUserid(), SaLoginConfig
                    .setExtra("username", loginUser.getUsername())
                    .setExtra("rememberMe", loginUser.isRememberMe()));

            LoginUser token = tokenService.getToken();
            tokenRefreshService.refreshToken();
            HttpHeaders headers = new HttpHeaders();
            headers.add(TokenConstant.AUTHORIZATION, token.getToken());
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, TokenConstant.AUTHORIZATION);
            return AjaxResult.success("ok", headers, null);
        } catch (ServiceException e) {
            return AjaxResult.fail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return AjaxResult.fail("ng");
        }
    }

    /**
     * 刷新token
     *
     * @return json
     */
    @PostMapping("/refresh")
    public AjaxResult refresh() {
        LoginUser loginUser = tokenRefreshService.reLogin();
        return AjaxResult.success(loginUser.getToken());
    }

    /**
     * logout
     *
     * @return json
     */
    @PostMapping("/logout")
    public AjaxResult logout() {
        StpUtil.logout();
        tokenRefreshService.logout();
        return AjaxResult.success();
    }

    /**
     * logout
     *
     * @return json
     */
    @PostMapping("/test")
    public AjaxResult test() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String message = messageSource.getMessage("info.login.success");
        return AjaxResult.success(tokenInfo);
    }
}
