package com.yq.security.core.authentication.restful;

import com.yq.security.core.utils.JsonLoginUtil;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * @Auther: yq
 * @Date: 2018-10-28 17:51
 * @Description:
 */
public class RestRememberMeServices extends PersistentTokenBasedRememberMeServices {


    public RestRememberMeServices(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
        String paramValue = JsonLoginUtil.getParams(request,parameter);
        if (paramValue != null && (paramValue.equalsIgnoreCase("true") || paramValue.equalsIgnoreCase("on") || paramValue.equalsIgnoreCase("yes") || paramValue.equals("1"))) {
            return true;
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Did not send remember-me cookie (principal did not set parameter '" + parameter + "')");
            }
            return false;
        }
    }
}
