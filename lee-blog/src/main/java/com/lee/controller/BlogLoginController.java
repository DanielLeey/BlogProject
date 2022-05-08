package com.lee.controller;

import com.lee.domain.ResponseResult;
import com.lee.domain.entity.entity.User;
import com.lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BlogLoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        return userService.logout();
    }
}
