package com.yq.security.core.properties;

import com.google.code.kaptcha.util.Config;
import javafx.util.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;

/**
 * @Auther: yq
 * @Date: 2018-11-01 10:16
 * @Description:
 */

@Getter
@Setter
public class KapatchaConfigBuilder implements Builder<Config> {

    //边框
    private String border = "yes";
    //边框颜色
    private String borderColor = "105,179,90";
    //字体颜色
    private String textproducerFontColor = "blue";
    //图片宽度
    private String imageWidth = "110";
    //图片高度
    private String imageHeight = "48";
    //字体大小
    private String textproducerFontSize = "30";
    //session主键
    private String sessionKey = "code";
    //字符数
    private String textproducerCharLength = "4";
    //字体
    private String textproducerFontNames = "宋体,楷体,微软雅黑";


    @Override
    public Config build() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", border);
        properties.setProperty("kaptcha.border.color", borderColor);
        properties.setProperty("kaptcha.textproducer.font.color",textproducerFontColor);
        properties.setProperty("kaptcha.image.width", imageWidth);
        properties.setProperty("kaptcha.image.height", imageHeight);
        properties.setProperty("kaptcha.textproducer.font.size", textproducerFontSize);
        properties.setProperty("kaptcha.session.key", sessionKey);
        properties.setProperty("kaptcha.textproducer.char.length", textproducerCharLength);
        properties.setProperty("kaptcha.textproducer.font.names", textproducerFontNames);
        return new Config(properties);
    }
}
