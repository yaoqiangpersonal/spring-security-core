/**
 * 
 */
package com.yq.security.core.validate;

import com.yq.security.core.properties.SecurityConstants;


public interface ValidateCodeType {


	public static enum CustomValidateCodeType implements ValidateCodeType{
		/**
		 * 短信验证码
		 */
		SMS {
			@Override
			public String getParamNameOnValidate() {
				return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS.value();
			}
		},
		/**
		 * app图片验证码
		 */
		APP_IMAGE {
			@Override
			public String getParamNameOnValidate() {
				return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE.value();
			}
		},
		/**
		 * browser图片验证码
		 */
		BROWSER_IMAGE {
			@Override
			public String getParamNameOnValidate() {
				return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE.value();
			}
		};

	}

	/**
	 * 校验时从请求中获取的参数的名字
	 * @return
	 */
	String getParamNameOnValidate();

}
