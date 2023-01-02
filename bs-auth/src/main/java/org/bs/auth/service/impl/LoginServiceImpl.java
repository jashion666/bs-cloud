package org.bs.auth.service.impl;

import cn.hutool.json.JSONUtil;
import org.bs.api.system.model.LoginUser;
import org.bs.api.system.remote.service.RemoteUserService;
import org.bs.api.system.repository.entity.MUserEntity;
import org.bs.auth.model.LoginBody;
import org.bs.auth.service.ILoginService;
import org.bs.common.core.domain.AjaxResult;
import org.bs.common.core.enums.ResultCodeEnum;
import org.bs.common.core.exception.ServiceException;
import org.bs.common.i18n.config.NacosI18nMessageSource;
import org.bs.satoken.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * login impl
 *
 * @author :wkh
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private NacosI18nMessageSource messageService;

    @Override
    public LoginUser login(LoginBody loginBody) {

        String username = loginBody.getUsername();
        AjaxResult.Body body = remoteUserService.getByUsername(username);
        if (body == null || ResultCodeEnum.RESULT_SUCCESS_CODE.getCode() != body.getCode()) {
            throw new ServiceException(messageService.getMessage("error.login"));
        }
        MUserEntity userInfo = JSONUtil.parse(body.getData()).toBean(MUserEntity.class);
        if (!PasswordHelper.matchesPassword(userInfo.getPassword(), loginBody.getPassword(), userInfo.getSalt())) {
            throw new ServiceException(messageService.getMessage("error.login"));
        }

        return LoginUser.builder().userid(userInfo.getId()).username(userInfo.getUsername()).build();
    }
}
