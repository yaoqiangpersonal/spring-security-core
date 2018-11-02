package com.yq.security.core.authorization.request.voter;

import com.yq.security.core.authorization.request.AbstractAccessDecisionVoter;
import com.yq.security.core.authorization.request.attribute.MethodConfigAttribute;
import com.yq.security.core.authorization.request.grantedAuthority.MethodGrantedAuthority;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Auther: yq
 * @Date: 2018-10-18 16:52
 * @Description: 方法投票器
 */
public class MethodVoter extends AbstractAccessDecisionVoter {

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return MethodConfigAttribute.class.isAssignableFrom(configAttribute.getClass());
    }

    @Override
    protected boolean supports(GrantedAuthority grantedAuthority) {
        return MethodGrantedAuthority.class.isAssignableFrom(grantedAuthority.getClass());
    }

    @Override
    protected boolean match(String attribute, String authority) {
        if("ALL".equals(authority))
            return true;
        return attribute.equals(authority);
    }
}
