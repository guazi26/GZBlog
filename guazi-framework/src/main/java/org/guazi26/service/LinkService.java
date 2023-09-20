package org.guazi26.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.guazi26.domain.ResponseResult;
import org.guazi26.domain.entity.Link;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-09-20 23:34:30
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

