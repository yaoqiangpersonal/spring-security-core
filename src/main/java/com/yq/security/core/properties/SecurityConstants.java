/**
 * 
 */
package com.yq.security.core.properties;

/**
 * @author zhailiang
 *
 */
public enum SecurityConstants {
	
	/**
	 * 默认的处理验证码的url前缀
	 */
	DEFAULT_VALIDATE_CODE_URL_PREFIX (0,"/defaultKaptcha"),
	/**
	 * 当请求需要身份认证时，默认跳转的url
	 */
	DEFAULT_UNAUTHENTICATION_URL_JSON (1,"/authentication/json"),
	/**
	 * 默认的用户名密码登录请求处理url
	 */
	DEFAULT_LOGIN_PROCESSING_BROWSER_URL_FORM (2,"/authentication/browser/form"),

	/**
	 * 默认的用户名密码登录请求处理url
	 */
	DEFAULT_LOGIN_PROCESSING_APP_URL_FORM (11,"/authentication/app/form"),

	/**
	 * 默认的手机验证码登录请求处理url
	 */
	DEFAULT_LOGIN_PROCESSING_URL_MOBILE (3,"/authentication/mobile"),
	/**
	 * 默认登录页面
	 */
	DEFAULT_LOGIN_PAGE_URL (4,"/imooc-signIn.html"),
	/**
	 * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
	 */
	DEFAULT_PARAMETER_NAME_CODE_IMAGE (5,"imageCode"),
	/**
	 * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
	DEFAULT_PARAMETER_NAME_CODE_SMS (6, "smsCode"),
	/**
	 * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
	 */
	DEFAULT_PARAMETER_NAME_MOBILE (7,"mobile"),
	/**
	 * session失效默认的跳转地址
	 */
	DEFAULT_SESSION_INVALID_URL(8,"/session/invalid"),

	/**
	 * 登出url
	 */
	DEFAULT_LOGOUT_PROCESSING_URL_FORM (9,"/logout"),

	/**
	 * 登出url
	 */
	DEFAULT_HOME (10,"/"),
	/**
	 * 检验用户
	 */
	DEFAULT_CHECK_USER (11,"/check");

	private final int id;
	private final String value;

	SecurityConstants(int id,String value){
		this.id = id;
		this.value = value;
	}

	public int id() {
		return id;
	}

	public String value() {
		return value;
	}
}
