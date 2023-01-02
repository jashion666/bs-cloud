package org.bs.satoken.service;

import cn.dev33.satoken.jwt.SaJwtTemplate;
import cn.dev33.satoken.jwt.SaJwtUtil;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONObject;
import org.bs.api.system.model.LoginUser;
import org.bs.common.core.constant.TokenConstant;
import org.bs.common.core.utils.StringUtil;
import org.bs.common.redis.RedisClient;
import org.bs.satoken.config.SaTokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :wkh
 */
@Service
public class TokenRefreshService {

    @Autowired
    private SaTokenProperties saTokenProperties;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private JwtService jwtService;

    /**
     * refresh token and re login
     *
     * @return LoginUser
     */
    public LoginUser reLogin() {
        LoginUser jwtUser = getJwtUser();
        StpUtil.logout();
        StpUtil.login(jwtUser.getUserid(), SaLoginConfig
                .setExtra("username", jwtUser.getUsername())
                .setExtra("rememberMe", jwtUser.isRememberMe()));
        refreshToken();
        return tokenService.getToken();
    }

    /**
     * refresh token
     */
    public void refreshToken() {
        LoginUser jwtUser = getJwtUser();
        // if rememberMe use rememberMeTimeout. otherwise use timeout
        long expireTime = jwtUser.isRememberMe() ? saTokenProperties.getRememberMeTimeout() : saTokenProperties.getTimeout();
        long tokenTime = expireTime + (TokenConstant.REFRESH_TOKEN_MINUTE * TokenConstant.MILLIS_MINUTE);
        // this token saved in database ,expireTime is more than expireTime, current is 60 minutes more
        String token = jwtService.createToken(jwtUser.getUserid().toString(), jwtUser.getUsername(), tokenTime);
        // save or update refresh token
        redisClient.set(userTokenKey(jwtUser), token, tokenTime);
    }

    /**
     * verify refresh token
     */
    public boolean verifyRefreshToken(boolean deleteExpireRefreshToken) {
        LoginUser jwtUser = getJwtUser();
        String tokenKey = userTokenKey(jwtUser);
        String token = redisClient.get(tokenKey);
        if (StringUtil.isEmpty(token)) {
            return false;
        }
        boolean isValid = jwtService.verify(token);
        if (!isValid && deleteExpireRefreshToken) {
            redisClient.remove(tokenKey);
        }
        return isValid;
    }

    /**
     * logout
     */
    public void logout() {
        LoginUser jwtUser = getJwtUser();
        redisClient.remove(userTokenKey(jwtUser));
    }

    /**
     * get user key
     *
     * @param loginUser loginUser
     * @return user key
     */
    private String userTokenKey(LoginUser loginUser) {
        return TokenConstant.USER_TOKEN_PREFIX + loginUser.getUserid();
    }

    /**
     * get jwt user user access token
     *
     * @return LoginUser
     */
    private LoginUser getJwtUser() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String accessToken = tokenInfo.getTokenValue();
        JSONObject payloads = SaJwtUtil.getPayloadsNotCheck(accessToken, StpUtil.getLoginType(), StpUtil.getStpLogic().getConfig().getJwtSecretKey());
        String userid = payloads.get(SaJwtTemplate.LOGIN_ID).toString();
        return LoginUser.builder()
                .userid(Integer.parseInt(userid))
                .token(accessToken)
                .username(payloads.get("username").toString())
                .rememberMe(Boolean.parseBoolean(payloads.get("rememberMe").toString()))
                .tokenKey(tokenInfo.getTokenName()).build();

    }

}
