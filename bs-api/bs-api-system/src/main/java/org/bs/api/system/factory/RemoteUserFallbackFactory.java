package org.bs.api.system.factory;

import org.bs.api.system.remote.service.RemoteUserService;
import org.bs.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author wkh
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public AjaxResult.Body getByUsername(String username) {
                return AjaxResult.fail("服务暂不可用").getData();
            }

            @Override
            public AjaxResult.Body test() {
                return AjaxResult.fail("服务暂不可用").getData();
            }
        };
    }
}
