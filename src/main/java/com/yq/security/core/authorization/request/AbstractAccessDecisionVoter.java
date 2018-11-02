package com.yq.security.core.authorization.request;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Auther: yq
 * @Date: 2018-10-30 11:13
 * @Description:
 */
public abstract class AbstractAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

    @Override
    public boolean supports(Class aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation o, Collection<ConfigAttribute> collection) {
        if(authentication == null)
            return ACCESS_DENIED;
        Iterator<ConfigAttribute> itr =   collection.iterator();
        Iterator<? extends GrantedAuthority> itr2 = authentication.getAuthorities().iterator();
        ConfigAttribute attribute;
        GrantedAuthority grantedAuthority;
        do {
            if (!itr.hasNext())
                return ACCESS_DENIED;
            attribute = itr.next();
        } while (!supports(attribute));

        do{
            if(!itr2.hasNext())
                return ACCESS_DENIED;
            grantedAuthority = itr2.next();
        }while(!supports(grantedAuthority));

        if(match(attribute.getAttribute(),grantedAuthority.getAuthority()))
            return ACCESS_GRANTED;
        return ACCESS_DENIED;
    }

    protected abstract boolean supports(GrantedAuthority grantedAuthority);

    protected abstract boolean match(String attribute,String authority);
}
