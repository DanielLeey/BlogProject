package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.constants.SystemConstants;
import com.lee.dao.CategoryMapper;
import com.lee.domain.entity.Article;
import com.lee.domain.entity.Category;
import com.lee.domain.vo.CategoryVo;
import com.lee.service.ArticleService;
import com.lee.service.CategoryService;
import com.lee.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public List<CategoryVo> getCategoryList() {
        //查询所有article,正式的
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> list = articleService.list(articleWrapper);
        Set<Long> categoryIds = list.stream().distinct().map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        List<Category> categories = listByIds(categoryIds);
        List<Category> normalCategories = categories.stream().filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        return BeanCopyUtils.copyBeanList(normalCategories, CategoryVo.class);
    }
}
