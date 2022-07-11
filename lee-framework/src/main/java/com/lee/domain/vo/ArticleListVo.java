package com.lee.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author Lee
 * 返回给首页HomeContent
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {

    private List<ArticleList> articleLists;

    private Integer total;
}
