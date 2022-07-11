package com.lee.runner;

import com.lee.constants.RedisKeyConstants;
import com.lee.domain.entity.Article;
import com.lee.domain.entity.ArticleCount;
import com.lee.service.ArticleCountService;
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
public class ArticleCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleCountService articleCountService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        List<ArticleCount> articleCountList = articleCountService.list();

        //查询文章信息，构造id-viewCount map 存入redis
        Map<String, Integer> viewCountMap = articleCountList.stream()
                .collect(Collectors.toMap(articleCount -> articleCount.getId().toString(), articleCount -> articleCount.getViewCount().intValue()));
        Map<String, Integer> commentCountMap = articleCountList.stream()
                .collect(Collectors.toMap(articleCount -> articleCount.getId().toString(), articleCount -> articleCount.getCommentCount().intValue()));
        Map<String, Integer> likeCountMap = articleCountList.stream()
                .collect(Collectors.toMap(articleCount -> articleCount.getId().toString(), articleCount -> articleCount.getLikeCount().intValue()));
        Map<String, Integer> collectCountMap = articleCountList.stream()
                .collect(Collectors.toMap(articleCount -> articleCount.getId().toString(), articleCount -> articleCount.getCollectCount().intValue()));

        redisCache.setCacheMap(RedisKeyConstants.VIEW_COUNT, viewCountMap);
        redisCache.setCacheMap(RedisKeyConstants.COMMENT_COUNT, commentCountMap);
        redisCache.setCacheMap(RedisKeyConstants.LIKE_COUNT, likeCountMap);
        redisCache.setCacheMap(RedisKeyConstants.COLLECT_COUNT, collectCountMap);

    }
}
