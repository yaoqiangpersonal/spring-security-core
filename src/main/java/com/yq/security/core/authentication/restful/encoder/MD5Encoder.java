package com.yq.security.core.authentication.restful.encoder;

import com.yq.common.utils.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Auther: yq
 * @Date: 2018-10-28 18:24
 * @Description:
 */
public class MD5Encoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return MD5Util.encode((String)charSequence);
    }

    @Override
    public boolean matches(CharSequence inputPassword, String dataBasePassword) {
        return dataBasePassword.equals(MD5Util.encode((String) inputPassword));
    }
}
