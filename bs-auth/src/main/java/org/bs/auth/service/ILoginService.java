package org.bs.auth.service;


import org.bs.auth.model.LoginBody;
import org.bs.api.system.model.LoginUser;

/**
 * user login service
 *
 * @author :wkh
 */
public interface ILoginService {

    /**
     * execute user login
     *
     * @param loginBody form body [username,password]
     * @return LoginUser
     */
    LoginUser login(LoginBody loginBody);
}
