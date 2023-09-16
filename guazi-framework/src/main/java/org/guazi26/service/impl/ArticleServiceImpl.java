package org.guazi26.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.guazi26.dao.ArticleDao;
import org.guazi26.domain.entity.Article;
import org.guazi26.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章表(Article)表服务实现类
 *
 * @author makejava
 * @since 2023-09-16 20:55:40
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

}

