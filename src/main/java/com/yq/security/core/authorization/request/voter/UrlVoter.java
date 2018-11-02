package com.yq.security.core.authorization.request.voter;

import com.yq.security.core.authorization.request.AbstractAccessDecisionVoter;
import com.yq.security.core.authorization.request.attribute.UrlConfigAttribute;
import com.yq.security.core.authorization.request.grantedAuthority.UrlGrantedAuthority;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.AntPathMatcher;

/**
 * @Auther: yq
 * @Date: 2018-10-18 16:49
 * @Description: url投票器
 */
public class UrlVoter extends AbstractAccessDecisionVoter {

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return UrlConfigAttribute.class.isAssignableFrom(configAttribute.getClass());
    }

    @Override
    protected boolean supports(GrantedAuthority grantedAuthority) {
        return UrlGrantedAuthority.class.isAssignableFrom(grantedAuthority.getClass());
    }

    @Override
    protected boolean match(String attribute, String authority) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(attribute,authority);
    }

}
