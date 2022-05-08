package com.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.domain.entity.Category;
import com.lee.domain.vo.CategoryVo;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<CategoryVo> getCategoryList();
}
