package com.yq.security.core.authentication.restful;

import com.yq.security.core.utils.JsonLoginUtil;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: yq
 * @Date: 2018-10-28 17:01
 * @Description:
 */
public class RestAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return JsonLoginUtil.getParams(request,getPasswordParameter());
    }
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return JsonLoginUtil.getParams(request,getUsernameParameter());
    }

}
