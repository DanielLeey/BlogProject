package com.lee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.constants.SystemConstants;
import com.lee.dao.CommentMapper;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.Comment;
import com.lee.domain.entity.User;
import com.lee.domain.vo.CommentVo;
import com.lee.enums.AppHttpCodeEnum;
import com.lee.service.CommentService;
import com.lee.service.UserService;
import com.lee.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public List<CommentVo> getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //先查询文章的根评论，根据文章id 和  rootId
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //如果类型是文章类型设置此查询条件
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType), Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, SystemConstants.ROOT_COMMENT);
        //评论类型 分为 文章 和 友链
        queryWrapper.eq(Comment::getType, commentType);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Comment> commentList = page.getRecords();
        List<CommentVo> commentVoList = commentToCommitVo(commentList);

        //查询子评论
        for (CommentVo commentVo : commentVoList) {
            List<CommentVo> subComments = getSubComments(commentVo);
            if (Objects.nonNull(subComments)) {
                commentVo.setChildren(subComments);
            }
        }
        return commentVoList;
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //评论类容不能为空
        if (Objects.isNull(comment.getContent())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.COMMENT_NOT_NULL);
        }
        //注意设置自动填充 创建人 创建时间 更新人 更新时间
        save(comment);
        return ResponseResult.okResult();
    }

    //根据一条评论获取子评论
    private List<CommentVo> getSubComments(CommentVo commentVo) {
        //查询子评论 root_id = comment.id
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, commentVo.getId());
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> list = list(queryWrapper);
        //如果children不为空 则继续递归查询子评论的 children
        if (list != null) {
            List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
            for (CommentVo commentVo1 : commentVos) {
                List<CommentVo> commentVos1CommentVos = getSubComments(commentVo1);
                commentVo1.setChildren(commentVos1CommentVos);
            }
            return commentVos;
        }
        //子评论为空则返回空
        return null;
    }

    private List<CommentVo> commentToCommitVo(List<Comment> commentList) {
        return commentList.stream().map(comment -> {
            //为评论设置评论用户名
            CommentVo commentVo = BeanCopyUtils.copyBean(comment, CommentVo.class);
            User commentUserId = userService.getById(comment.getCreateBy());
            commentVo.setUsername(commentUserId.getNickName());
            //为评论设置 所回复的目标评论的nickname
            Long toCommentUserId = comment.getToCommentUserId();
            if (Objects.nonNull(toCommentUserId) && toCommentUserId != -1) {
                User toCommentUser = userService.getById(toCommentUserId);
                commentVo.setToCommentUserName(toCommentUser.getNickName());
            }
            return commentVo;
        }).collect(Collectors.toList());
    }
}
