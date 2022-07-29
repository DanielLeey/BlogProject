package com.lee.controller;

import com.lee.domain.ResponseResult;
import com.lee.domain.entity.UserReadHistory;
import com.lee.enums.AppHttpCodeEnum;
import com.lee.service.UserReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liyansong
 * @Date: 2022/7/29 17:23
 * @Version: 1.0
 * 用户浏览记录管理Controller
 */
@RestController
@Api(tags = "用户浏览文章历史管理接口")
@RequestMapping("/user/readHistory")
public class UserReadHistoryController {

    @Autowired
    private UserReadHistoryService userReadHistoryService;

    @ApiOperation("创建浏览记录")
    @PostMapping(value = "/create")
    public ResponseResult create(@RequestBody UserReadHistory userReadHistory) {
        int count = userReadHistoryService.create(userReadHistory);
        if (count > 0) {
            return ResponseResult.okResult(count);
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.MONGODB_CREATE_FAIL);
        }
    }

    @ApiOperation("删除浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@RequestParam("ids") List<String> ids) {
        int count = userReadHistoryService.delete(ids);
        if (count > 0) {
            return ResponseResult.okResult(count);
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.MONGODB_DELETE_FAIL);
        }
    }

    @ApiOperation("展示浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<UserReadHistory> list(Long userId) {
        return userReadHistoryService.list(userId);
    }
}
