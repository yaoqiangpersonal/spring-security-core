package com.yq.security.core.properties;

import lombok.Data;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

/**
 * @Auther: yq
 * @Date: 2018-10-28 17:59
 * @Description:
 */
@Data
public class RememberMeProperties {

    private final static String DEFAULT_KEY = "INTERNAL_SECRET_KEY";

    private final static int TOKEN_VALIDITY_SECONDS = 1209600;

    private String key = DEFAULT_KEY;

    private String parameter = AbstractRememberMeServices.DEFAULT_PARAMETER;

    private int tokenValiditySeconds = TOKEN_VALIDITY_SECONDS;

}
