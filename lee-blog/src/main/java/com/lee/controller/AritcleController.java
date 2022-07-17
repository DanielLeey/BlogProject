package com.lee.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Article;
import com.lee.domain.entity.User;
import com.lee.domain.vo.*;
import com.lee.service.ArticleService;
import com.lee.service.EsArticleService;
import com.lee.service.UserService;
import com.lee.utils.BeanCopyUtils;
import com.lee.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

    @Autowired
    private UserService userService;

    @Autowired
    private EsArticleService esArticleService;

    /**
     * @param pageSize    每一页的数据条数，默认5条
     * @param currentPage 当前的页数
     * @param isLatest    是否按最新排序
     * @param articleType 文章类型
     * @return
     */
    @GetMapping("/list")
    public ArticleListVo getArticleList(@RequestParam("pageSize") int pageSize, @RequestParam("currentPage") int currentPage,
                                        @RequestParam(value = "latest", required = false, defaultValue = "true") boolean isLatest, @RequestParam(value = "articleType", required = false) String articleType) {
        List<ArticleList> articleLists = articleService.getArticleList(pageSize, currentPage, isLatest, articleType);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(articleType)) {
            wrapper.eq(Article::getCategoryId, articleType);
        }
        int count = articleService.count(wrapper);
        ArticleListVo articleListVo = new ArticleListVo(articleLists, count);
        return articleListVo;
    }

    @PostMapping("/save")
    public boolean saveArticle(@RequestBody Article article) {
        // 获取文章创建人id，获取不到就获取系统当前用户id
        // User currentUser = userService.getCurrentUser();
        Long creatorId = Optional.ofNullable(article.getCreateBy()).orElse(SecurityUtils.getUserId());
        User user = userService.getById(creatorId);
        article.setCreatorName(user.getUserName());
        boolean save = articleService.save(article);
        return save;
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
        articleService.updateViewCount(id);
        return articleService.getArticleDetail(id);
    }

    @GetMapping("/searchTag")
    public Set<String> getArticleTags() {
        List<Article> articleList = articleService.list();
        Set<String> result = new HashSet<>();
        for (Article article : articleList) {
            String articleTags = article.getArticleTags();
            String[] tags = new String[0];
            if (articleTags != null) {
                tags = articleTags.split(",");
            }
            Collections.addAll(result, tags);
        }
        return result;
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

    @RequestMapping("/search")
    public ArticleListVo articleSearch(@RequestParam("keywords") String keywords) {
        Page<Article> articlePage = esArticleService.search(keywords, 0, 5);
        List<Article> articles = articlePage.getContent();
        List<ArticleList> articleLists = BeanCopyUtils.copyBeanList(articles, ArticleList.class).stream().map(articleList -> {
            String articleTags = articleList.getArticleTags();
            if (!StringUtils.hasText(articleTags)) {
                return articleList;
            }
            articleList.setTags(Arrays.asList(articleTags.split(",")));
            return articleList;
        }).collect(Collectors.toList());
        ArticleListVo articleListVo = new ArticleListVo(articleLists, articleLists.size());
        return articleListVo;
    }
}
