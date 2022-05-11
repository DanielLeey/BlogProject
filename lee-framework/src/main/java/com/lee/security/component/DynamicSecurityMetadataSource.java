package com.lee.security.component;

import cn.hutool.core.util.URLUtil;
import com.lee.security.service.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: liyansong
 * @Date: 2022/5/10 9:59
 * @Version: 1.0
 * 获取当前访问路径所需要的资源
 */
@Component
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    //通过调用方法获取所有的权限
    private static Map<String, ConfigAttribute> configAttributeMap = new ConcurrentHashMap<>();

    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    //容器启动的时候执行，可作为一些数据的常规化加载
    @PostConstruct
    private void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    /**
     *
     * @param object
     * @return
     * @throws IllegalArgumentException
     * 获取当前访问路径所需要的资源
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //如果所有的权限为空，则需要载入
        if (configAttributeMap == null) {
            this.loadDataSource();
        }
        //定义返回当前访问路径所需的资源
        List<ConfigAttribute> attributes = new ArrayList<>();

        //获取当前访问路径URL
        //获得path部分 URI -> http://www.aaa.bbb/search?scope=ccc&q=ddd PATH -> /search
        final String requestUrl = ((FilterInvocation) object).getRequestUrl();
        String path = URLUtil.getPath(requestUrl);
        //匹配当前路径和map中的存入的所有路径
        PathMatcher pathMatcher = new AntPathMatcher();

        Iterator<String> iterator = configAttributeMap.keySet().iterator();
        while(iterator.hasNext()) {
            String pattern = iterator.next();
            if (pathMatcher.match(pattern, path)) {
                attributes.add(configAttributeMap.get(pattern));
            }
        }
        // 未设置操作请求权限，返回空集合
        return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
