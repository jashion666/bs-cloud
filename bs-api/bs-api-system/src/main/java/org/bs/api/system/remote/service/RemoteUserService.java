package org.bs.api.system.remote.service;

import org.bs.api.system.factory.RemoteUserFallbackFactory;
import org.bs.common.core.constant.ServiceNameConstants;
import org.bs.common.core.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 *
 * @author wkh
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    AjaxResult.Body getByUsername(@PathVariable("username") String username);

}
