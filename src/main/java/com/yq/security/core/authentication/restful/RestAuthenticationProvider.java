package com.yq.security.core.authentication.restful;

import com.yq.security.core.exception.PasswordNotMatchException;
import javafx.util.Builder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Auther: yq
 * @Date: 2018-10-28 16:54
 * @Description:
 */
public class RestAuthenticationProvider extends DaoAuthenticationProvider {

    /**
     * 构建器
     */
    public static class RestFulAuthenticationProviderBuilder implements Builder<RestAuthenticationProvider> {

        private final UserDetailsService userDetailsService;

        private boolean hideUserNotFoundExceptions = true;

        private final PasswordEncoder encoder;

        private RestFulAuthenticationProviderBuilder(UserDetailsService userDetailsService,PasswordEncoder encoder){
            this.userDetailsService = userDetailsService;
            this.encoder = encoder;
        }

        public RestFulAuthenticationProviderBuilder setHideUserNotFoundExceptions(boolean hideUserNotFoundExceptions){
            this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
            return this;
        }

        //传入一个构建器，生成对象
        @Override
        public RestAuthenticationProvider build() {
            return new RestAuthenticationProvider(this);
        }
    }

    //接收一个构建器，有参构造
    private RestAuthenticationProvider(RestFulAuthenticationProviderBuilder builder) {
        super();
        setPasswordEncoder(builder.encoder);
        setHideUserNotFoundExceptions(builder.hideUserNotFoundExceptions);
        setUserDetailsService(builder.userDetailsService);
    }


    /**
     * 接收必要参数，创建一个构建器对象，静态方法
     *
     * @param userDetailsService    密码校验类
     * @param encoder   密码编译方法
     * @return  建造器
     */
    public static RestFulAuthenticationProviderBuilder custom(UserDetailsService userDetailsService,PasswordEncoder encoder){
        return new RestFulAuthenticationProviderBuilder(userDetailsService,encoder);
    }

    /**
     * 密码核对
     *
     * @param userDetails       获取到的user
     * @param authentication    生成的token
     * @throws AuthenticationException 抛出的坏凭证异常
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
                this.logger.debug("Authentication failed: password does not match stored value");
                throw new PasswordNotMatchException("密码错误");
            }
        }
    }

}
