package org.guazi26.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.guazi26.constant.SystemConstants;
import org.guazi26.dao.CategoryDao;
import org.guazi26.domain.ResponseResult;
import org.guazi26.domain.entity.Article;
import org.guazi26.domain.entity.Category;
import org.guazi26.domain.vo.CategoryVo;
import org.guazi26.service.ArticleService;
import org.guazi26.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.guazi26.service.CategoryService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-09-17 05:58:42
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService{

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表，状态为已发布
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(queryWrapper);

        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());

        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }
}
