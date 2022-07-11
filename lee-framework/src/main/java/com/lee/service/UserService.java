package com.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.User;
import com.lee.domain.vo.UserInfoVo;
import com.lee.domain.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {
    Map<String, Object> login(User user);

    ResponseResult logout();

    UserInfoVo userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    /**
     * 获取当前系统用户
     * @return
     */
    User getCurrentUser();
}
