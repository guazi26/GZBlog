package org.guazi26.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.guazi26.domain.entity.Article;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-16 20:55:39
 */
@Mapper
public interface ArticleDao extends BaseMapper<Article> {

}

