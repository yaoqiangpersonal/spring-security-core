package com.yq.security.core.validate.impl;

import com.yq.security.core.validate.ValidateCodeType;
import com.yq.security.core.validate.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: yq
 * @Date: 2018-11-01 17:38
 * @Description:
 */
public abstract class AbstractImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        HttpServletResponse response = request.getResponse();

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }
}
