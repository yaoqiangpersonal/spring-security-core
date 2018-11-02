/**
 * 
 */
package com.yq.security.core.validate.sms;

import com.yq.security.core.properties.SecurityConstants;
import com.yq.security.core.validate.ValidateCode;
import com.yq.security.core.validate.ValidateCodeGenerator;
import com.yq.security.core.validate.ValidateCodeType;
import com.yq.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 短信验证码处理器
 * 
 * @author zhailiang
 *
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE.value();
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		smsCodeSender.send(mobile, validateCode.getCode());
	}

	@Override
	public ValidateCodeType getSupport() {
		return ValidateCodeType.CustomValidateCodeType.SMS;
	}

	@Override
	public boolean support(ValidateCodeGenerator generator) {
		return generator.getClass().isAssignableFrom(SmsCodeGenerator.class);
	}
}
