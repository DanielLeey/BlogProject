package com.lee.controller;

import com.lee.annotation.SystemLog;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.User;
import com.lee.domain.vo.UserInfoVo;
import com.lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public UserInfoVo userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody com.lee.domain.entity.User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@Validated @RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/check")
    public ResponseResult checkToken() {
        return ResponseResult.okResult("ok");
    }

}
