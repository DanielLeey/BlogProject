package com.lee.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    private Long id;

    //文章id
    private Long articleId;

    //根评论id
    private Long rootId;

    //评论内容
    private String content;

    //回复目标评论id
    private Long toCommentId;

    //所回复的目标评论的userid
    private Long toCommentUserId;

    //***所回复的目标评论的nickname
    private String toCommentUserName;

    //***评论人的nickname
    private String username;
    //评论人的id
    private Long createBy;

    private Date createTime;

    private List<CommentVo> children;

}
