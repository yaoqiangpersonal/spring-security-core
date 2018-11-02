package com.yq.security.core.authentication.mobile;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Auther: yq
 * @Date: 2018-11-02 16:13
 * @Description:
 */
public interface SmsCodeUserDetailsService {
    UserDetails loadUserByMobile(String mobile);
}
