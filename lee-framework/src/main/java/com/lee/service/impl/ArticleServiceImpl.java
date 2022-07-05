package com.lee.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.constants.SystemConstants;
import com.lee.dao.ArticleMapper;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Article;
import com.lee.domain.entity.Category;
import com.lee.domain.vo.ArticleDetailVo;
import com.lee.domain.vo.ArticleListVo;
import com.lee.domain.vo.PageVo;
import com.lee.service.ArticleService;
import com.lee.service.CategoryService;
import com.lee.utils.BeanCopyUtils;
import com.lee.utils.RedisCache;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: admin
 * @Date: 2022/4/2 11:39
 * @Version: 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<Article> hotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page(1,10);
        page(page, wrapper);
        return page.getRecords();
    }

    @Override
    public PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        //如果有分类id，查询分类id对应的文章
        articleWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        //查询正式发布的文章
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //置顶文章降序
        articleWrapper.orderByDesc(Article::getIsTop);
        //设置分页
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, articleWrapper);
        //根据分类查询所有
        List<Article> articles = page.getRecords();
        //查询article的分类id，查询对应的分类名，设置到article中
        articles.stream().map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName())).collect(Collectors.toList());
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        //封装成Page返回
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return pageVo;
    }

    @Override
    public ArticleDetailVo getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //根据分类id查询分类名
        Category category = categoryService.getById(article.getCategoryId());
        if (category != null) {
            article.setCategoryName(category.getName());
        }
        //从redis中获取文章的浏览量
        Integer viewCount = redisCache.getCacheMapValue("Article:ViewCount", article.getId().toString());
        article.setViewCount(viewCount.longValue());
        //放入ArticleDetailVo
        return BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //修改redis中hash key 中 key的value
        redisCache.incrCacheMapValue("Article:ViewCount", id.toString(), 1);
        return ResponseResult.okResult();
    }

    @Override
    public List<ArticleListVo> getArticleList(int pageSize, int currentPage, boolean isLatest, String articleType) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        // 根据文章创建时间最新排序
        if (isLatest) {
            wrapper.orderByDesc(Article::getCreateTime);
        }
        // 根据文章类型分类
        if (StrUtil.isNotBlank(articleType)) {
            wrapper.eq(Article::getCategoryId, articleType);
        }
        Page<Article> page = new Page<>(currentPage, pageSize);
        page(page, wrapper);
        List<Article> articles = page.getRecords();
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class).stream().map(articleListVo -> {
            String articleTags = articleListVo.getArticleTags();
            if (!StringUtils.hasText(articleTags)) {
                return articleListVo;
            }
            articleListVo.setTags(Arrays.asList(articleTags.split(",")));
            return articleListVo;
        }).collect(Collectors.toList());
        return articleListVos;
    }
}
