package com.lee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.dao.ResourceMapper;
import com.lee.domain.entity.Resource;
import com.lee.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    /**
     * 根据用户id获取用户resources
     * @param id
     * @return
     */
    @Override
    public List<Resource> listByUserId(Long id) {
        //根据用户id user_role 获取roleId，role_resource 获取resources
        List<Resource> resources = resourceMapper.listByUserId(id);

        return resources;
    }
}
