package org.bs.auth.controller;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.bs.api.system.model.LoginUser;
import org.bs.api.system.remote.service.RemoteUserService;
import org.bs.auth.model.LoginBody;
import org.bs.auth.service.ILoginService;
import org.bs.common.core.constant.TokenConstant;
import org.bs.common.core.domain.AjaxResult;
import org.bs.common.core.exception.ServiceException;
import org.bs.common.core.validator.ValidatorGroup3;
import org.bs.common.i18n.config.NacosI18nMessageSource;
import org.bs.satoken.service.ITokenService;
import org.bs.satoken.service.TokenRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :wkh
 */
@RestController
@Slf4j
public class TokenController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private TokenRefreshService tokenRefreshService;

    @Autowired
    private NacosI18nMessageSource messageSource;

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 认证
     *
     * @return json
     */
    @PostMapping("/authentication")
    public AjaxResult authentication(@Validated(ValidatorGroup3.class) @RequestBody LoginBody loginBody) {

        try {
            LoginUser loginUser = loginService.login(loginBody);
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
        return AjaxResult.success(remoteUserService.test());
    }
}
