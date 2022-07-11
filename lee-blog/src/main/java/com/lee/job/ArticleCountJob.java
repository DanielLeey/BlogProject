package com.lee.job;

import com.lee.constants.RedisKeyConstants;
import com.lee.domain.entity.ArticleCount;
import com.lee.service.ArticleCountService;
import com.lee.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 文章浏览量、评论数、点赞数、收藏数定时刷新到数据库
 */
@Component
public class ArticleCountJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleCountService articleCountService;

    //秒 分 时 日 月 星期
    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateArticleViewCount() {
        System.out.println("print :" + new Date().toString());
        //获取redis中浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(RedisKeyConstants.VIEW_COUNT);
        Map<String, Integer> commentCountMap = redisCache.getCacheMap(RedisKeyConstants.COMMENT_COUNT);
        Map<String, Integer> likeCountMap = redisCache.getCacheMap(RedisKeyConstants.LIKE_COUNT);
        Map<String, Integer> collectCountMap = redisCache.getCacheMap(RedisKeyConstants.COLLECT_COUNT);
        Set<String> ids = new HashSet<>();
        ids.addAll(viewCountMap.keySet());
        ids.addAll(commentCountMap.keySet());
        ids.addAll(likeCountMap.keySet());
        ids.addAll(collectCountMap.keySet());

        List<ArticleCount> articleCountList = ids.stream().
                map(id -> new ArticleCount(Long.valueOf(id))).collect(Collectors.toList());
        for(ArticleCount articleCount : articleCountList) {
            String id = String.valueOf(articleCount.getId());
            Integer viewCount = viewCountMap.get(id);
            Integer commentCount = commentCountMap.get(id);
            Integer likeCount = likeCountMap.get(id);
            Integer collectCount = collectCountMap.get(id);
            if(viewCount != null) {
                articleCount.setViewCount(Long.valueOf(viewCount));
            }
            if(commentCount != null) {
                articleCount.setCommentCount(Long.valueOf(commentCount));
            }
            if(likeCount != null) {
                articleCount.setLikeCount(Long.valueOf(likeCount));
            }
            if(collectCount != null) {
                articleCount.setCollectCount(Long.valueOf(collectCount));
            }
        }

        articleCountService.updateBatchById(articleCountList);
    }
}
