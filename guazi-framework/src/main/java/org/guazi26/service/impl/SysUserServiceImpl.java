package org.guazi26.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.guazi26.dao.SysUserDao;
import org.guazi26.domain.entity.SysUser;
import org.guazi26.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-09-23 02:45:18
 */
@Service("userService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

}
