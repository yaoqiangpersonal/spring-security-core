package com.yq.security.core.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.yq.security.core.authentication.restful.RestRememberMeServices;
import com.yq.security.core.authorization.request.RequestAccessDecisionManager;
import com.yq.security.core.authorization.request.RequestSecurityInterceptor;
import com.yq.security.core.authorization.request.RequestSecurityMetadataSource;
import com.yq.security.core.authorization.request.voter.MethodVoter;
import com.yq.security.core.authorization.request.voter.UrlVoter;
import com.yq.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @Auther: yq
 * @Date: 2018-10-18 17:28
 * @Description: 安全配置
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 配置资源决策器
     *
     * @return
     */
    public RequestAccessDecisionManager getRequestAccessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> voters = new LinkedList<>();
        voters.add(new MethodVoter());
        voters.add(new UrlVoter());
        return new RequestAccessDecisionManager(voters);
    }

    /**
     * 定义资源角色授权器
     *
     * @return
     */
    public RequestSecurityMetadataSource getRequestMetadataSourceService() {
        return new RequestSecurityMetadataSource();
    }

    /**
     * 定义授权拦截器
     *
     * @return
     */
/*    @Bean
    public RequestSecurityInterceptor getRequestSecurityInterceptor() {
        RequestSecurityInterceptor requestAccessDecisionManager = new RequestSecurityInterceptor(getRequestMetadataSourceService());
        requestAccessDecisionManager.setAccessDecisionManager(getRequestAccessDecisionManager());
        return requestAccessDecisionManager;
    }*/

    @Bean
    public RememberMeServices restRememberMeServices(){
        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
        // 此处需要设置数据源，否则无法从数据库查询验证信息
        rememberMeTokenRepository.setDataSource(dataSource);
        // 此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
        // rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new RestRememberMeServices(securityProperties.getRememberMe().getKey(), userDetailsService, rememberMeTokenRepository);

        // 该参数不是必须的，默认值为 "remember-me", 但如果设置必须和页面复选框的 name 一致
        rememberMeServices.setParameter(securityProperties.getRememberMe().getParameter());

        return rememberMeServices;
    }


}
