/**
 * 
 */
package com.yq.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhailiang
 *
 */
@Getter
@Setter
public class BrowserProperties {
	
	private SessionProperties session = new SessionProperties();
	
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL.value();
	
	private LoginResponseType loginType = LoginResponseType.JSON;

	private KapatchaConfigBuilder kapatcha = new KapatchaConfigBuilder();
	
}
