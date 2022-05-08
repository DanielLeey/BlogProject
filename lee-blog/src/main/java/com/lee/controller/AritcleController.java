package com.lee.controller;

import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Article;
import com.lee.domain.vo.ArticleDetailVo;
import com.lee.domain.vo.ArticleListVo;
import com.lee.domain.vo.HotArticleVo;
import com.lee.domain.vo.PageVo;
import com.lee.service.ArticleService;
import com.lee.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: admin
 * @Date: 2022/4/2 11:50
 * @Version: 1.0
 */
@RestController
@RequestMapping("/aritcle")
public class AritcleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public List<Article> test(){
        return articleService.list();
    }

    @GetMapping("/hotArticleList")
    public List<HotArticleVo> hotArticleList(){
        //查询热点文章
        List<Article> articles =  articleService.hotArticleList();
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
     * @return
     */
    @RequestMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }
}
