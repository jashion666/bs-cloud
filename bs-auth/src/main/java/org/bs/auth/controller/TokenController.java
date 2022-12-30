package org.bs.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.bs.common.core.domain.AjaxResult;
import org.bs.common.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * logout
     *
     * @return json
     */
    @PostMapping("/logout")
    public AjaxResult logout() {
        redisClient.set("a", 1, 10L);
        return AjaxResult.success("d");
    }

}
