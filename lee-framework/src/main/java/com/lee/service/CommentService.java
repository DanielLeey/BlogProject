package com.lee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Comment;
import com.lee.domain.vo.CommentVo;

import java.util.List;

public interface CommentService extends IService<Comment> {
    List<CommentVo> getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
