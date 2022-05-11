package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.dao.UserMapper;
import com.lee.domain.ResponseResult;
import com.lee.security.entity.LoginUserDetails;
import com.lee.domain.entity.entity.User;
import com.lee.domain.vo.UserInfoVo;
import com.lee.enums.AppHttpCodeEnum;
import com.lee.exception.SystemException;
import com.lee.service.UserService;
import com.lee.utils.BeanCopyUtils;
import com.lee.security.utils.JwtTokenUtil;
import com.lee.utils.RedisCache;
import com.lee.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public Map<String, Object> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //认证
        //调用authenticationManager,需要再blog定义配置类注入
        //需要传入Authentication, 创建UsernamePasswordAuthenticationToken对象
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断认证是否成功
        if (Objects.isNull(authenticate)) {
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        // 获取认证后的对象
        LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
        // 通过认证后的对象，生成jwt Token，sub：username
        String token = jwtTokenUtil.generateToken(loginUserDetails);
        // 不在此处放入redis 因为登录时需要查询数据库，重复登录压力大
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUserDetails.getUser(), UserInfoVo.class);
        // 放入map返回
        Map<String, Object> map = new HashMap<>(2);
        map.put("token", token);
        map.put("userInfo", userInfoVo);
        return map;
    }

    @Override
    public ResponseResult logout() {
        //token SecurityContextHolder中获取user
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
//        Long userId = loginUserDetails.getUser().getId();
        Long userId = SecurityUtils.getUserId();
        //redis中删除用户key
        redisCache.deleteObject("bloglogin:" + userId);
        return ResponseResult.okResult();
    }

    @Override
    public UserInfoVo userInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = getById(userId);
        return BeanCopyUtils.copyBean(user, UserInfoVo.class);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if (userNameExist(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (nickNameExist(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //对密码加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        save(user);
        return ResponseResult.okResult();
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getNickName, nickName);
        int count = count(wrapper);
        return count > 0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        int count = count(wrapper);
        return count > 0;
    }
}
