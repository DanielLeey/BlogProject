package com.lee.security.filter;

import com.lee.security.component.DynamicAccessDecisionManager;
import com.lee.security.component.DynamicSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: liyansong
 * @Date: 2022/5/10 9:05
 * @Version: 1.0
 */
public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    /**
     * 注入DynamicAccessDecisionManager，调用父类的setter,设置自己的鉴权manager
     * @param dynamicAccessDecisionManager
     */
    @Autowired
    public void setAccessDecisionManager(DynamicAccessDecisionManager dynamicAccessDecisionManager) {
        super.setAccessDecisionManager(dynamicAccessDecisionManager);
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    /**
     * 获取当前访问路径所需资源
     * @return
     */
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return dynamicSecurityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 调用super.beforeInvocation(fi)方法时会调用AccessDecisionManager中的decide方法用于鉴权操作，
     * 而decide方法中的configAttributes参数会通过SecurityMetadataSource中的getAttributes方法来获取，
     * configAttributes其实就是配置好的访问当前接口所需要的权限
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //OPTIONS请求直接放行
        if(request.getMethod().equals(HttpMethod.OPTIONS.toString())){
            //fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        //doFilter调用前，此处会调用AccessDecisionManager中的decide方法进行鉴权操作
        InterceptorStatusToken token = super.beforeInvocation(fi);
        //鉴权之后放行，dofilter之后再进入finally，执行afterInvocation
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            //doFilter调用后，token中获取SecurityContext，设置SecurityContextHolder.setContext
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {

    }
}
