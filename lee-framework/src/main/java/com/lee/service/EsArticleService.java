package com.lee.service;

import com.lee.domain.entity.Article;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Lee
 * es文章搜索管理
 */
public interface EsArticleService {
    /**
     * 从数据库中导入所有文章到ES
     */
    int importAll();

    /**
     * 根据id删除文章
     */
    void delete(Long id);

    /**
     * 根据id创建文章
     */
    Article create(Long id);

    /**
     * 批量删除文章
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索文章标题、内容、摘要
     */
    Page<Article> search(String keyword, Integer pageNum, Integer pageSize);
}
