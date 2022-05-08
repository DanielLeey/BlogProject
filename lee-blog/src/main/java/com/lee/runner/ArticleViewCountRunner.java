package com.lee.runner;

import com.lee.domain.entity.Article;
import com.lee.service.ArticleService;
import com.lee.utils.RedisCache;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 文章浏览量启动时，将数据库中的数据加载到redis中
 */
@Component
public class ArticleViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleService.list();
        articles.stream().map(new Function<Article, Integer>() {
            @Override
            public Integer apply(Article article) {
                return article.getViewCount().intValue();
            }
        });
        //查询文章信息，构造id-viewCount map 存入redis
        Map<String, Integer> viewCountMap = articles.stream().collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        redisCache.setCacheMap("Article:ViewCount", viewCountMap);
    }
}
