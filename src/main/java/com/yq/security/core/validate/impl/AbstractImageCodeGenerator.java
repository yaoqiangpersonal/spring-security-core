package com.yq.security.core.validate.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yq.security.core.properties.SecurityProperties;
import com.yq.security.core.validate.ValidateCode;
import com.yq.security.core.validate.ValidateCodeException;
import com.yq.security.core.validate.ValidateCodeGenerator;
import com.yq.security.core.validate.ValidateCodeType;
import com.yq.security.core.validate.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @Auther: yq
 * @Date: 2018-11-01 11:36
 * @Description:
 */
public abstract class AbstractImageCodeGenerator implements ValidateCodeGenerator {


    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        if(defaultKaptcha == null)
            defaultKaptcha = createDefaultKaptcha();
        String validateCode = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(validateCode);

        return new ImageCode(image, validateCode, securityProperties.getCode().getImage().getExpireIn());
    }


    public void setDefaultKaptcha(DefaultKaptcha defaultKaptcha){
        this.defaultKaptcha = defaultKaptcha;
    }

    protected abstract DefaultKaptcha createDefaultKaptcha();

}
