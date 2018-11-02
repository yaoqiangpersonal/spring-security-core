package com.yq.security.core.authorization.request.attribute;

import org.springframework.security.access.ConfigAttribute;

/**
 * @Auther: yq
 * @Date: 2018-10-30 10:16
 * @Description:
 */
public class MethodConfigAttribute implements ConfigAttribute {

    private final String method;

    public MethodConfigAttribute(String method){
        this.method = method;
    }

    @Override
    public String getAttribute() {
        return method;
    }
}
