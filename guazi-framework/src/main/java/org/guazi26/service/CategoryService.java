package org.guazi26.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.guazi26.domain.ResponseResult;
import org.guazi26.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-09-17 05:58:41
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

