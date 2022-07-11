package com.lee.domain.entity;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (ArticleIndex)表实体类
 *
 * @author lee
 * @since 2022-07-10 10:26:29
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article_count")
public class ArticleCount {

    public ArticleCount(Long id) {
        this.id = id;
    }

    //文章id@TableId
    private Long id;

    //浏览量
    private Long viewCount;
    //评论数
    private Long commentCount;
    //点赞数
    private Long likeCount;
    //收藏数
    private Long collectCount;

}
