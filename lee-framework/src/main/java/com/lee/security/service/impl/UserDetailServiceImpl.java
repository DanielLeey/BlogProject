package com.lee.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.security.entity.LoginUserDetails;
import com.lee.domain.entity.Resource;
import com.lee.enums.AppHttpCodeEnum;
import com.lee.exception.SystemException;
import com.lee.service.ResourceService;
import com.lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.lee.domain.entity.entity.User;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名去DB 查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userService.getOne(wrapper);
        if (Objects.isNull(user)) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_EXIST);
        }
        //返回用户信息
        // TODO 查询权限信息封装
        List<Resource> resources = resourceService.listByUserId(user.getId());
        //自定义UserDetails实现类
        LoginUserDetails loginUserDetails = new LoginUserDetails(user, resources);
        return loginUserDetails;
    }
}
