package com.lee.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: admin
 * @Date: 2022/4/2 16:48
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {

    private Long id;
    //标题
    private String title;

    //访问量
    private Long viewCount;
}
