/**
 * 
 */
package com.yq.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author zhailiang
 *
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private SmsCodeUserDetailsService smsCodeUserDetailsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

		UserDetails user = smsCodeUserDetailsService.loadUserByMobile((String)authenticationToken.getPrincipal()) ;

		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		
		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public SmsCodeUserDetailsService getUserDetailsService() {
		return smsCodeUserDetailsService;
	}

	public void setUserDetailsService(SmsCodeUserDetailsService smsCodeUserDetailsService) {
		this.smsCodeUserDetailsService = smsCodeUserDetailsService;
	}

}
