package com.yq.security.core.authorization.request.grantedAuthority;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Auther: yq
 * @Date: 2018-10-30 11:05
 * @Description:
 */
public class MethodGrantedAuthority implements GrantedAuthority {

    private final String method;

    public MethodGrantedAuthority(final String method){
        this.method = method;
    }

    @Override
    public String getAuthority() {
        return method;
    }
}
