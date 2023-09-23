package org.guazi26.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.guazi26.domain.ResponseResult;
import org.guazi26.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-09-22 03:26:04
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

