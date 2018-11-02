package com.yq.security.core.authorization.request.attribute;

import org.springframework.security.access.ConfigAttribute;

/**
 * @Auther: yq
 * @Date: 2018-10-30 10:14
 * @Description:
 */
public class UrlConfigAttribute implements ConfigAttribute {

    private final String url;

    public UrlConfigAttribute(String url){
        this.url = url;
    }

    @Override
    public String getAttribute() {
        return url;
    }
}
