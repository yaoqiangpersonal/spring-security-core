package com.yq.security.core.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @Auther: yq
 * @Date: 2018-10-28 16:58
 * @Description:
 */
public class PasswordNotMatchException extends AuthenticationException {


    public PasswordNotMatchException(String detail) {
        super(detail);
    }

    public PasswordNotMatchException(String detail, Throwable ex) {
        super(detail, ex);
    }
}
