package com.lee.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Article;
import com.lee.domain.vo.ArticleDetailVo;
import com.lee.domain.vo.ArticleListVo;
import com.lee.domain.vo.HotArticleVo;
import com.lee.domain.vo.PageVo;
import com.lee.service.ArticleService;
import com.lee.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: admin
 * @Date: 2022/4/2 11:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article")
public class AritcleController {

    @Autowired
    private ArticleService articleService;

    /**
     * @param pageSize    每一页的数据条数，默认5条
     * @param currentPage 当前的页数
     * @param isLatest    是否按最新排序
     * @return
     */
    @GetMapping("/list")
    public List<ArticleListVo> getArticleList(@RequestParam("pageSize") int pageSize, @RequestParam("currentPage") int currentPage,
                                              @RequestParam(value = "latest", required = false) boolean isLatest, @RequestParam(value = "articleType", required = false) String articleType) {
        List<ArticleListVo> articleListVos = articleService.getArticleList(pageSize, currentPage, isLatest, articleType);
        return articleListVos;
    }

    @GetMapping("/hotArticleList")
    public List<HotArticleVo> hotArticleList() {
        //查询热点文章
        List<Article> articles = articleService.hotArticleList();
        final List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return hotArticleVos;
    }

    @GetMapping("/articleList")
    public PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @GetMapping("/{id}")
    public ArticleDetailVo getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }


    /**
     * 更新文章浏览量，修改redis中数据
     *
     * @return
     */
    @RequestMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }
}
