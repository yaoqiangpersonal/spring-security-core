package com.yq.security.core.validate.image;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.yq.security.core.properties.SecurityProperties;
import com.yq.security.core.validate.ValidateCodeType;
import com.yq.security.core.validate.impl.AbstractImageCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: yq
 * @Date: 2018-11-01 14:43
 * @Description:
 */
public class AppImageCodeGenerator extends AbstractImageCodeGenerator {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected DefaultKaptcha createDefaultKaptcha() {
        Config c = securityProperties.getApp().getKapatcha().build();
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(c);
        return defaultKaptcha;
    }

}
