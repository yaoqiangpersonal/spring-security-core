package com.yq.security.core.authorization.request.grantedAuthority;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Auther: yq
 * @Date: 2018-10-30 11:02
 * @Description:
 */
public class UrlGrantedAuthority implements GrantedAuthority {
    private final String url;

    public UrlGrantedAuthority(final String url){
        this.url = url;
    }

    @Override
    public String getAuthority() {
        return url;
    }
}
