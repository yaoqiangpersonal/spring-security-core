package com.yq.security.core.authentication.restful;

import com.yq.security.core.authentication.AbstractChannelSecurityConfig;
import com.yq.security.core.authentication.restful.encoder.MD5Encoder;
import com.yq.security.core.authentication.restful.handler.RestAuthenticationFailureHandler;
import com.yq.security.core.authentication.restful.handler.RestAuthenticationSuccessHandler;
import com.yq.security.core.properties.SecurityConstants;
import com.yq.security.core.properties.SecurityProperties;
import com.yq.security.core.validate.ValidateConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Auther: yq
 * @Date: 2018-10-28 17:23
 * @Description:
 */
@Component("restAuthenticationSecurityConfig")
public class RestAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private RememberMeServices rememberMeServices;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(restAuthenticationProvider())
                .addFilterBefore(restAuthenticationFilter(http),UsernamePasswordAuthenticationFilter.class)

                .logout()
                //指定登出的url
                .logoutUrl(SecurityConstants.DEFAULT_LOGOUT_PROCESSING_URL_FORM.value())
                .permitAll();
    }

    /**
     *
     * 修改很少参数
     * @return
     */
    private AuthenticationProvider restAuthenticationProvider(){
        return RestAuthenticationProvider
                .custom(userDetailsService,new MD5Encoder())
                .setHideUserNotFoundExceptions(false)
                .build();
    }

    private RestAuthenticationFilter restAuthenticationFilter(HttpSecurity http) throws Exception {
        RestAuthenticationFilter restAuthenticationFilter = new RestAuthenticationFilter();
        restAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        restAuthenticationFilter.setAuthenticationSuccessHandler(new RestAuthenticationSuccessHandler());
        restAuthenticationFilter.setAuthenticationFailureHandler(new RestAuthenticationFailureHandler());
        restAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL_JSON.value(), "POST"));
        restAuthenticationFilter.setRememberMeServices(rememberMeServices);
        return restAuthenticationFilter;
    }




}
