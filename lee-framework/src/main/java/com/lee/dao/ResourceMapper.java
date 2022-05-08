package com.lee.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.domain.entity.Resource;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 根据用户id获取对应resources
     * @param id
     * @return
     */
    public List<Resource> listByUserId(Long id);
}
