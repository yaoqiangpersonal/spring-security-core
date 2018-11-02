/**
 * 
 */
package com.yq.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhailiang
 *
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.security")
public final class SecurityProperties {

	private final RememberMeProperties rememberMe = new RememberMeProperties();

	private BrowserProperties browser = new BrowserProperties();

	private AppProperties app = new AppProperties();

	private ValidateCodeProperties code = new ValidateCodeProperties();

}

