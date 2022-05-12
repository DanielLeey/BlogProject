package com.lee.service.impl;

import com.lee.annotation.CacheException;
import com.lee.security.entity.LoginUserDetails;
import com.lee.service.UserCacheService;
import com.lee.utils.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: liyansong
 * @Date: 2022/5/12 19:24
 * @Version: 1.0
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCacheServiceImpl.class);

    @Autowired
    private RedisCache redisCache;

    @Override
    //@CacheException
    public LoginUserDetails getLoginUserDetails(String username) {
        return (LoginUserDetails) redisCache.getCacheObject("bloglogin:" + username);
    }

    @Override
    public void setLoginUserDetails(String key, LoginUserDetails loginUserDetails) {
        redisCache.setCacheObject("bloglogin:" + loginUserDetails.getUsername(), loginUserDetails);
    }


    @Override
    public void delUser(Long userId) {

    }

    @Override
    public void delResourceList(Long userId) {

    }

    @Override
    public void delResourceListByRole(Long roleId) {

    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {

    }

    @Override
    public void delResourceListByResource(Long resourceId) {

    }
}
