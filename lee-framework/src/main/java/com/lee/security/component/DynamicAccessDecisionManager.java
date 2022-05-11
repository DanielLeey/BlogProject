package com.lee.security.component;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: liyansong
 * @Date: 2022/5/10 9:54
 * @Version: 1.0
 */
@Component
public class DynamicAccessDecisionManager implements AccessDecisionManager {

    /**
     * 动态权限决策管理器，用于判断用户是否有访问权限
     * metadataSource中存入的路径所需要的权限，比对用户所拥有的权限
     * @param authentication 用户认证，包含所拥有的访问权限
     * @param object
     * @param configAttributes matadataSource中设置的访问路径所需要的资源
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //如果configAttributes为null，表示未设置所需要的访问资源，直接返回
        if (CollUtil.isEmpty(configAttributes)) {
            return;
        }
        //访问路径所需资源与用户有的权限对比，有就返回
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            String needAuthority = configAttribute.getAttribute();
            for(GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("抱歉，您没有访问权限！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
