package com.yq.security.core.authentication.restful;

import com.yq.common.utils.Msg;
import com.yq.security.core.utils.ResponseWriter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: yq
 * @Date: 2018-10-30 17:14
 * @Description:
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        ResponseWriter
                .custom(response)
                .setStatus(HttpStatus.FORBIDDEN.value())
                .build()
                .writeJson(Msg.FORBIDDEN);
    }
}
