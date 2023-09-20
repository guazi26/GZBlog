package org.guazi26.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.guazi26.domain.entity.Link;


/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-20 23:34:27
 */

@Mapper
public interface LinkDao extends BaseMapper<Link> {

}
