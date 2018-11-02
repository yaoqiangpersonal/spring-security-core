/**
 * 
 */
package com.yq.security.core.validate;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateCodeProcessorHolder {

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	public ValidateCodeProcessor findValidateCodeProcessor(String type) {

		return findValidateCodeProcessor(Enum.valueOf(ValidateCodeType.CustomValidateCodeType.class,type.toUpperCase()));
	}

	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		Iterator<ValidateCodeProcessor> itr = validateCodeProcessors.values().iterator();
		ValidateCodeProcessor processor;
			do{
				if(!itr.hasNext())
					throw new ValidateCodeException("验证码处理器" + type.getClass().getSimpleName() + "不存在");
				processor = itr.next();
			}while(!processor.support(type));

		return processor;
	}

}
