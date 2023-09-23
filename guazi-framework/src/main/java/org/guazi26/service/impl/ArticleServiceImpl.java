package org.guazi26.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.guazi26.constant.SystemConstants;
import org.guazi26.dao.ArticleDao;
import org.guazi26.domain.ResponseResult;
import org.guazi26.domain.entity.Article;
import org.guazi26.domain.entity.Category;
import org.guazi26.domain.vo.ArticleDetailVo;
import org.guazi26.domain.vo.ArticleListVo;
import org.guazi26.domain.vo.HotArticleVo;
import org.guazi26.domain.vo.PageVo;
import org.guazi26.service.ArticleService;
import org.guazi26.service.CategoryService;
import org.guazi26.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2023-09-16 20:55:40
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询热门文章
     *
     * @return
     */
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        //不能是草稿
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多查询10条
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

        List<Article> records = page.getRecords();

        /*ArrayList<HotArticleVo> list = new ArrayList<>();

        for (Article record : records) {
            HotArticleVo hotArticleVo = new HotArticleVo();
            BeanUtils.copyProperties(record, hotArticleVo);
            list.add(hotArticleVo);
        }*/

        List<HotArticleVo> hotArticleVo = BeanCopyUtils.copyBeanList(records, HotArticleVo.class);

        return ResponseResult.okResult(hotArticleVo);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        //有无categotyId，状态是正式发布的，置顶文章显示在最前面（对isTop进行降序排序）
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getIsTop);


        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        //通过categoryId查找对应的categoryName，并封装到Article中
        List<Article> articleList = page.getRecords();

        for (Article article : articleList) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }


        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articleList, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }


    /**
     * 根据id查询文章
     *
     * @param id
     * @return
     */
    @Override
    public ResponseResult getArticleDetail(Long id) {

        Article article = getById(id);

        Category category = categoryService.getById(article.getCategoryId());

        if (category != null) {
            article.setCategoryName(category.getName());
        }
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);

        return ResponseResult.okResult(articleDetailVo);
    }
}

