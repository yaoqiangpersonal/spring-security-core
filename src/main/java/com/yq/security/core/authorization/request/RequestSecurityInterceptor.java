package com.yq.security.core.authorization.request;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * @Auther: yq
 * @Date: 2018-10-29 16:58
 * @Description:
 */
public class RequestSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {


    private final SecurityMetadataSource securityMetadataSource;

    public RequestSecurityInterceptor(SecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest,servletResponse,filterChain);
        this.invoke(fi);
    }

    private void invoke(FilterInvocation fi){
        //主要执行方法
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(),fi.getResponse());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }finally {
            super.afterInvocation(token,(Object)null);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}
