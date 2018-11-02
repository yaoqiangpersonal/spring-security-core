package com.yq.security.core.authorization.request;

import com.yq.security.core.authorization.request.attribute.MethodConfigAttribute;
import com.yq.security.core.authorization.request.attribute.UrlConfigAttribute;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashSet;

/**
 * @Auther: yq
 * @Date: 2018-10-29 17:50
 * @Description:
 */
public class RequestSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if(supports(o.getClass())){
            HttpServletRequest request = ((FilterInvocation)o).getRequest();
            UrlConfigAttribute url = new UrlConfigAttribute(request.getRequestURI());
            MethodConfigAttribute method = new MethodConfigAttribute(request.getMethod());
            HashSet<ConfigAttribute> set = new HashSet<>();
            set.add(url);
            set.add(method);
            return set;
        }else{
            throw new IllegalArgumentException(o.getClass() + "is not supports");
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
