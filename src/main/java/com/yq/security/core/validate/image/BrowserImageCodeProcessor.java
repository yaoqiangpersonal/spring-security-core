package com.yq.security.core.validate.image;

import com.yq.security.core.validate.ValidateCode;
import com.yq.security.core.validate.ValidateCodeGenerator;
import com.yq.security.core.validate.ValidateCodeType;
import com.yq.security.core.validate.impl.AbstractImageCodeProcessor;
import com.yq.security.core.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Auther: yq
 * @Date: 2018-11-01 17:34
 * @Description:
 */
@Component("browserImageCodeProcessor")
public class BrowserImageCodeProcessor extends AbstractImageCodeProcessor {

    @Override
    public ValidateCodeType getSupport() {
        return ValidateCodeType.CustomValidateCodeType.BROWSER_IMAGE;
    }

    @Override
    public boolean support(ValidateCodeGenerator generator) {

        return generator.getClass().isAssignableFrom(BrowserImageCodeGenerator.class);
    }
}
