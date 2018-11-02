/**
 * 
 */
package com.yq.security.core.validate.sms;

/**
 * @author zhailiang
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
