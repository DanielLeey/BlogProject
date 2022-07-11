package com.lee.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleList {

    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //所属分类名
    private String categoryName;
    //缩略图
    private String thumbnail;

    //标签
    private String articleTags;

    private List<String> tags;

    //访问量
    private Long viewCount;

    private String creatorName;

    private Long createBy;

    private Date createTime;
}
