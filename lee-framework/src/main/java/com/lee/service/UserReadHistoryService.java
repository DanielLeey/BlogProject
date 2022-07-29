package com.lee.service;

import com.lee.domain.entity.UserReadHistory;

import java.util.List;

/**
 * @Author: liyansong
 * @Date: 2022/7/29 17:16
 * @Version: 1.0
 */
public interface UserReadHistoryService {
    /**
     * 生成浏览记录
     */
    int create(UserReadHistory userReadHistory);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<UserReadHistory> list(Long userId);
}
