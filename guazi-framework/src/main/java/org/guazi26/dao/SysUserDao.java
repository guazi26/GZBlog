package org.guazi26.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.guazi26.domain.entity.SysUser;


/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-21 00:37:50
 */

@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

}
