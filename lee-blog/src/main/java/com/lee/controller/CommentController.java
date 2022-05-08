package com.lee.controller;

import com.lee.constants.SystemConstants;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Comment;
import com.lee.domain.vo.CommentVo;
import com.lee.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public List<CommentVo> getCommentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.getCommentList(SystemConstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }

    //查询友联评论接口
    @GetMapping("/linkCommentList")
    public List<CommentVo> addLinkComment(Integer pageNum, Integer pageSize) {
        return commentService.getCommentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }

    //评论接口
    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }


}
