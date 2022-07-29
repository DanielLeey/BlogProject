package com.lee.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author: liyansong
 * @Date: 2022/7/28 19:59
 * @Version: 1.0
 * 用户浏览文章历史记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserReadHistory {
    @Id
    private String id;

    @Indexed
    private Long userId;

    private String userName;

    private String nickName;

    //创建时间
    private Date createTime;

    @Indexed
    private Long articleId;

    private String title;

    private Long categoryId;

    private String articleTags;
}
