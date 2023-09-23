package org.guazi26.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.guazi26.domain.entity.Comment;


/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-22 03:26:01
 */

@Mapper
public interface CommentDao extends BaseMapper<Comment> {

}
