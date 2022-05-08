package com.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.domain.entity.Link;
import com.lee.domain.vo.LinkVo;

import java.util.List;

public interface LinkService extends IService<Link> {
    List<LinkVo> getAllLink();
}
