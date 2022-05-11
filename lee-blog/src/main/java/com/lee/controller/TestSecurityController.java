package com.lee.controller;

import com.lee.domain.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liyansong
 * @Date: 2022/5/10 11:46
 * @Version: 1.0
 */
@RestController
@RequestMapping("brand")
public class TestSecurityController {

    @RequestMapping("a")
    public ResponseResult test() {
        return ResponseResult.okResult();
    }
}
