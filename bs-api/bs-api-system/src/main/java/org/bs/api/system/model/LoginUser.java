package org.bs.api.system.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

    private String tokenKey;

    private Integer userid;

    private String username;

    private boolean rememberMe;

    private Long loginTime;

    private String ipaddr;

    private Set<String> permissions;

    private Set<String> roles;
}
