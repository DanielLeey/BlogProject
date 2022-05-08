package com.lee.job;

import com.lee.domain.entity.Article;
import com.lee.service.ArticleService;
import com.lee.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ArticleViewCountJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    //秒 分 时 日 月 星期
    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateArticleViewCount() {
        System.out.println("print :" + new Date().toString());
        //获取redis中浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("Article:ViewCount");
        //更新到数据库
        List<Article> updatedArticles = viewCountMap.entrySet().stream().map(new Function<Map.Entry<String, Integer>, Article>() {
            @Override
            public Article apply(Map.Entry<String, Integer> articleEntry) {
                Long id = Long.valueOf(articleEntry.getKey());
                Long viewCount = articleEntry.getValue().longValue();
                return new Article(id, viewCount);
            }
        }).collect(Collectors.toList());
        articleService.updateBatchById(updatedArticles);
    }
}
