package com.lee.security.service.impl;

import com.lee.domain.entity.Resource;
import com.lee.security.service.DynamicSecurityService;
import com.lee.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: liyansong
 * @Date: 2022/5/9 19:44
 * @Version: 1.0
 */
@Service
public class DynamicSecurityServiceImpl implements DynamicSecurityService {

    @Autowired
    public ResourceService resourceService;

    //DB查询所有的resources，放入map中
    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        List<Resource> resources = resourceService.list();
        for ( Resource resource : resources) {
            //map put resource url -> id:name
            map.put(resource.getUrl(), new SecurityConfig(resource.getId() + ":" + resource.getName()));
        }
        return map;
    }
}
