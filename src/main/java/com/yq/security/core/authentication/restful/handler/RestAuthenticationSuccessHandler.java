package com.yq.security.core.authentication.restful.handler;

import com.yq.common.utils.Msg;
import com.yq.security.core.utils.ResponseWriter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: yq
 * @Date: 2018-10-28 17:14
 * @Description:
 */
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseWriter
                .custom(httpServletResponse)
                .build()
                .writeJson(Msg.SUCCESS.add("user",authentication));
    }
}
