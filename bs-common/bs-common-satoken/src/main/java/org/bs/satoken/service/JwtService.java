package org.bs.satoken.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bs.common.core.exception.ServiceException;
import org.bs.common.core.utils.DateUtil;
import org.bs.satoken.config.SaTokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Jwt service
 *
 * @author wkh
 */
@Service
public class JwtService {


    @Autowired
    private SaTokenProperties saTokenProperties;

    /**
     * create jwt token
     *
     * @return String
     */
    public String createToken(String userid, String username, long expiresAt) {

        Algorithm algorithm = Algorithm.HMAC256(saTokenProperties.getSecret());
        return JWT
                .create()
                .withClaim("username", username)
                .withClaim("userid", userid)
                .withClaim("t", DateUtil.getIntUnixMilli())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiresAt * 1000))
                .sign(algorithm);
    }

    /**
     * verify token
     *
     * @param token verify
     * @return verify result
     */
    public boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(saTokenProperties.getSecret());
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * get username in token
     *
     * @return username
     */
    public String getUsername(String token) {
        return getClaimField(token, "username");
    }

    /**
     * get userid in token
     *
     * @return userid
     */
    public String getUserid(String token) {
        return getClaimField(token, "userid");
    }

    /**
     * get token field
     *
     * @param token token
     * @param key   key
     * @return value
     */
    public String getClaimField(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            throw new ServiceException("fail");
        }
    }

}
