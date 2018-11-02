package com.yq.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: yq
 * @Date: 2018-11-01 10:54
 * @Description:
 */
@Getter
@Setter
public class AppProperties {
    private KapatchaConfigBuilder kapatcha = new KapatchaConfigBuilder();
}
