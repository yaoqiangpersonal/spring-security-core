package com.yq.security.core.authentication.restful.handler;


import com.yq.common.utils.Msg;
import com.yq.security.core.exception.PasswordNotMatchException;
import com.yq.security.core.utils.ResponseWriter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.yq.common.utils.Msg.*;

/**
 * @Auther: yq
 * @Date: 2018-10-28 17:15
 * @Description:
 */
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        ResponseWriter
                .custom(httpServletResponse)
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .build()
                .writeJson(getMsgByException(e));
    }

    private Msg getMsgByException(AuthenticationException e){
        if(e instanceof UsernameNotFoundException){
            return USERNAME_ERROR;
        }else if(e instanceof PasswordNotMatchException){
            return PASSWORD_ERROR;
        }else{
            return FAIL;
        }
    }
}
