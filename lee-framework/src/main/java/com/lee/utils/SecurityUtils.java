package com.lee.utils;

import com.lee.security.entity.LoginUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * token中获取登录人的用户id
 *
 */
public class SecurityUtils {
    /**
     * 获取用户
     **/
    public static LoginUserDetails getLoginUser()
    {
        return (LoginUserDetails) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return id != null && 1L == id;
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}
