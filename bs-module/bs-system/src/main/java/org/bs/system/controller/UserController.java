package org.bs.system.controller;

import org.bs.api.system.model.LoginUser;
import org.bs.common.core.domain.AjaxResult;
import org.bs.common.core.utils.StringUtil;
import org.bs.common.i18n.config.NacosI18nMessageSource;
import org.bs.satoken.session.SessionUtil;
import org.bs.system.service.IMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :wkh
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IMUserService imUserService;

    @Autowired
    private NacosI18nMessageSource messageSource;

    /**
     * 通过用户名称查询用户信息
     *
     * @return json
     */
    @RequestMapping("/info/{username}")
    public AjaxResult getByUsername(@PathVariable("username") String username) {
        if (StringUtil.isEmpty(username)) {
            return AjaxResult.fail(messageSource.getMessage("error.input.required", "用户名"));
        }
        return AjaxResult.success(imUserService.getByUsername(username));
    }

    /**
     * ajax请求
     *
     * @return json
     */
    @RequestMapping("/test")
    public AjaxResult test() {
        LoginUser userInfo = SessionUtil.getUserInfo();
        return AjaxResult.success(userInfo);
    }

}
