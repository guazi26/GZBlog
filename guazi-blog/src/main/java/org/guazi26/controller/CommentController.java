package org.guazi26.controller;


import org.guazi26.constant.SystemConstants;
import org.guazi26.domain.ResponseResult;
import org.guazi26.domain.entity.Comment;
import org.guazi26.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {

        return commentService.commentList(SystemConstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }


    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment) {

        return commentService.addComment(comment);
    }


    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }
}
