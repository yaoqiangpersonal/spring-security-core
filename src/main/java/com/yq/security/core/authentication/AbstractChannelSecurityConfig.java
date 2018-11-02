package com.yq.security.core.authentication;

import com.yq.security.core.authentication.restful.handler.RestAuthenticationSuccessHandler;
import com.yq.security.core.properties.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @Auther: yq
 * @Date: 2018-10-28 16:43
 * @Description:
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter{

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL.value())
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_BROWSER_URL_FORM.value())
                .successHandler(new RestAuthenticationSuccessHandler())
                .failureForwardUrl(SecurityConstants.DEFAULT_LOGIN_PAGE_URL.value());

    }
}
