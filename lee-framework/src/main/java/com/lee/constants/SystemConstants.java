package com.lee.constants;

/**
 * @Author: admin
 * @Date: 2022/4/2 17:25
 * @Version: 1.0
 */
public class SystemConstants {
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     *  分类是正常分布状态
     */
    public static final String STATUS_NORMAL = "0";
    /**
     *  友链是正常分布状态
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /**
     * 评论是否是根评论
     */
    public static final Long ROOT_COMMENT = -1L;
    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友联评论
     */
    public static final String LINK_COMMENT = "1";
}
