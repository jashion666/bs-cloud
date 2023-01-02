package org.bs.satoken.session;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.bs.api.system.model.LoginUser;

/**
 * @author :wkh
 */
public class SessionUtil {

    /**
     * get login user information
     *
     * @return LoginUser
     */
    public static LoginUser getUserInfo() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return LoginUser.builder()
                .userid(Integer.parseInt(tokenInfo.getLoginId().toString()))
                .username(StpUtil.getExtra("username").toString())
                .rememberMe(Boolean.parseBoolean(StpUtil.getExtra("rememberMe").toString()))
                .build();
    }

    /**
     * get login user id
     *
     * @return user id
     */
    public static Integer getUserId() {
        return Integer.parseInt(StpUtil.getLoginId().toString());
    }


}
