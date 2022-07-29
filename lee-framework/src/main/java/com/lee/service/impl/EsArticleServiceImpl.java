package com.lee.service.impl;

import com.lee.respository.EsArticleRepository;
import com.lee.domain.entity.Article;
import com.lee.service.ArticleService;
import com.lee.service.EsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Lee
 * es文章搜索管理实现类
 */
@Service
public class EsArticleServiceImpl implements EsArticleService {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private EsArticleRepository esArticleRepository;

    @Override
    public int importAll() {
        List<Article> articleList = articleService.list();
        Iterable<Article> esArticleIterable = esArticleRepository.saveAll(articleList);
        Iterator<Article> iterator = esArticleIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        esArticleRepository.deleteById(id);
    }

    @Override
    public Article create(Long id) {
        Article result = null;
        Article article = articleService.getById(id);
        if (!ObjectUtils.isEmpty(article)) {
            result = esArticleRepository.save(article);
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<Article> articleList = new ArrayList<>();
            for (Long id : ids) {
                Article article = new Article();
                article.setId(id);
                articleList.add(article);
            }
            esArticleRepository.deleteAll(articleList);
        }
    }

    @Override
    public Page<Article> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esArticleRepository.findByTitleOrContentOrSummary(keyword, keyword, keyword, pageable);
    }


}
