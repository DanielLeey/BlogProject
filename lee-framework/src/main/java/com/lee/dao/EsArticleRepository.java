package com.lee.dao;

import com.lee.domain.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Lee
 * 继承ElasticsearchRepository接口可以获得常用的数据操作方法
 * 文章Article可通过ArticleRepository去es中查找
 */
public interface EsArticleRepository extends ElasticsearchRepository<Article, Long> {
    /**
     * 搜索查询
     *
     * @param title   文章标题
     * @param content 文章内容
     * @param summary 文章摘要
     * @return
     */
    Page<Article> findByTitleOrContentOrSummary(String title, String content, String summary, Pageable page);

}
