package com.lee.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Article;
import com.lee.domain.entity.User;
import com.lee.domain.entity.UserReadHistory;
import com.lee.domain.vo.*;
import com.lee.service.ArticleService;
import com.lee.service.EsArticleService;
import com.lee.service.UserReadHistoryService;
import com.lee.service.UserService;
import com.lee.utils.BeanCopyUtils;
import com.lee.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(tags = "文章管理接口")
public class AritcleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AritcleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private EsArticleService esArticleService;

    @Autowired
    private UserReadHistoryService userReadHistoryService;

    /**
     * @param pageSize    每一页的数据条数，默认5条
     * @param currentPage 当前的页数
     * @param isLatest    是否按最新排序
     * @param articleType 文章类型
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询所有文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true),
            @ApiImplicitParam(name = "currentPage", value = "页码", required = true),
            @ApiImplicitParam(name = "latest", value = "是否最新：1是0否", required = false),
            @ApiImplicitParam(name = "articleType", value = "分类id", required = false)
    })
    public ArticleListVo getArticleList(@RequestParam("pageSize") int pageSize,
                                        @RequestParam("currentPage") int currentPage,
                                        @RequestParam(value = "latest", required = false, defaultValue = "true") boolean isLatest,
                                        @RequestParam(value = "articleType", required = false) String articleType) {
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
    @ApiOperation("保存文章")
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
    @ApiOperation("查询热门文章")
    public List<HotArticleVo> hotArticleList() {
        //查询热点文章
        List<Article> articles = articleService.hotArticleList();
        final List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return hotArticleVos;
    }

    @GetMapping("/articleList")
    @ApiOperation("查询某一分类下所用文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = true)
    })
    public PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    @GetMapping("/{id}")
    @ApiOperation("查询文章详情")
    @ApiImplicitParam(name = "id", value = "文章id")
    public ArticleDetailVo getArticleDetail(@PathVariable("id") Long id) {
        articleService.updateViewCount(id);
        ArticleDetailVo articleDetail = articleService.getArticleDetail(id);
        // 添加历史记录
        try {
            User currentUser = userService.getCurrentUser();
            UserReadHistory userReadHistory = new UserReadHistory(currentUser, articleDetail);
            userReadHistoryService.create(userReadHistory);
        } catch (Exception e){
            LOGGER.info("用户未登录，不需要添加历史记录");
        }
        return articleDetail;
    }

    @GetMapping("/searchTag")
    @ApiOperation("查询所有文章标签")
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
    @GetMapping("/updateViewCount/{id}")
    @ApiOperation("更新文章浏览量")
    @ApiImplicitParam(name = "id", value = "文章id")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }

    @GetMapping("/search")
    @ApiOperation("搜索文章")
    @ApiImplicitParam(name = "keywords", value = "文章关键词")
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
