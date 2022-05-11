package com.lee.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.annotation.CacheException;
import com.lee.domain.entity.Resource;
import com.lee.domain.entity.entity.User;
import com.lee.enums.AppHttpCodeEnum;
import com.lee.exception.SystemException;
import com.lee.security.entity.LoginUserDetails;
import com.lee.service.ResourceService;
import com.lee.service.UserService;
import com.lee.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RedisCache redisCache;

    @Override
    @CacheException
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: 先从redis中查询，没有再从数据库查询
        LoginUserDetails loginUserDetails = null;
        try {
            loginUserDetails = (LoginUserDetails) redisCache.getCacheObject("bloglogin:" + username);
            if (Objects.nonNull(loginUserDetails)) {
                return loginUserDetails;
            }
        } catch (Throwable throwable) {
            System.out.println("error");
        }

        //根据用户名去DB 查询用户信息 查到之后封装LoginUserDetails存入redis中
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userService.getOne(wrapper);
        if (Objects.isNull(user)) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_EXIST);
        }
        //返回用户信息
        List<Resource> resources = resourceService.listByUserId(user.getId());
        //自定义UserDetails实现类，存入redis
        loginUserDetails = new LoginUserDetails(user, resources);
        try {
            redisCache.setCacheObject("bloglogin:" + loginUserDetails.getUsername(), loginUserDetails);
        } catch (Throwable throwable) {
            System.out.println("error2");
        }
        return loginUserDetails;
    }
}
