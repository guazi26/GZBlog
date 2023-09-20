package org.guazi26.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.guazi26.domain.entity.Category;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-17 05:58:36
 */

@Mapper
public interface CategoryDao extends BaseMapper<Category> {

}
