/**
 * 
 */
package com.yq.security.core.validate;

import com.yq.security.core.properties.SecurityProperties;
import com.yq.security.core.validate.image.AppImageCodeGenerator;
import com.yq.security.core.validate.image.BrowserImageCodeGenerator;
import com.yq.security.core.validate.sms.DefaultSmsCodeSender;
import com.yq.security.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhailiang
 *
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;

	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}


	@Bean
	@ConditionalOnMissingBean(name = "appImageCodeGenerator")
	public ValidateCodeGenerator appImageCodeGenerator() {
		ValidateCodeGenerator codeGenerator = new AppImageCodeGenerator();
		return codeGenerator;
	}

	@Bean
	@ConditionalOnMissingBean(name = "browserImageCodeGenerator")
	public ValidateCodeGenerator browserImageCodeGenerator() {
		ValidateCodeGenerator codeGenerator = new BrowserImageCodeGenerator();
		return codeGenerator;
	}

}
