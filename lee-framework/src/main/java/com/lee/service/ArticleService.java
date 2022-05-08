package com.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Article;
import com.lee.domain.vo.ArticleDetailVo;
import com.lee.domain.vo.ArticleListVo;
import com.lee.domain.vo.PageVo;

import java.util.List;

/**
 * @Author: admin
 * @Date: 2022/4/2 11:38
 * @Version: 1.0
 */
public interface ArticleService extends IService<Article> {

    List<Article> hotArticleList();

    PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ArticleDetailVo getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);
}
