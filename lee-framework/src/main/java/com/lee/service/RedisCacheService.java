package com.lee.service;

import java.util.List;

/**
 * @Author: liyansong
 * @Date: 2022/5/11 17:14
 * @Version: 1.0
 * @description: 后台用户缓存操作类
 */
public interface RedisCacheService {
    /**
     * 删除后台用户缓存
     */
    void delUser(Long userId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delResourceList(Long userId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResourceListByResource(Long resourceId);
}
