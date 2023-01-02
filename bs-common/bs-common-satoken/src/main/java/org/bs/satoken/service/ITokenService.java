package org.bs.satoken.service;


import org.bs.api.model.LoginUser;

/**
 * ITokenService
 *
 * @author :wkh
 */
public interface ITokenService {

    /**
     * refresh token
     */
    LoginUser getToken();

}
