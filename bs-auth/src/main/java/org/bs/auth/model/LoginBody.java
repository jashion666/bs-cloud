package org.bs.auth.model;

import lombok.Data;
import org.bs.api.validator.ValidatorGroup1;
import org.bs.api.validator.ValidatorGroup3;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author :wkh
 */
@Data
public class LoginBody implements Serializable {

    @NotBlank(message = "{error.input.required:用户名}", groups = ValidatorGroup3.class)
    private String username;

    @NotBlank(message = "{error.input.required:密码}", groups = ValidatorGroup3.class)
    private String password;

    @NotBlank(message = "{error.input.required:verifyCode}", groups = ValidatorGroup1.class)
    private String verifyCode;

    private boolean rememberMe;

    private String uuid;
}
