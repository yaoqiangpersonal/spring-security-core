/**
 * 
 */
package com.yq.security.core.authentication.mobile;

import com.yq.security.core.authentication.restful.handler.RestAuthenticationFailureHandler;
import com.yq.security.core.authentication.restful.handler.RestAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 */
@Component("smsCodeAuthenticationSecurityConfig")
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	
	@Autowired
	private SmsCodeUserDetailsService smsCodeUserDetailsService;

	@Autowired
	private RememberMeServices rememberMeServices;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(new RestAuthenticationSuccessHandler());
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(new RestAuthenticationFailureHandler());
		smsCodeAuthenticationFilter.setRememberMeServices(rememberMeServices);

		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
		smsCodeAuthenticationProvider.setUserDetailsService(smsCodeUserDetailsService);
		
		http.authenticationProvider(smsCodeAuthenticationProvider)
			.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

}
