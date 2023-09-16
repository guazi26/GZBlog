package org.guazi26.controller;



import org.guazi26.domain.entity.Article;
import org.guazi26.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章表(Article)表控制层
 *
 * @author makejava
 * @since 2023-09-16 20:55:38
 */
@RestController
@RequestMapping("article")
public class ArticleController{

    @Autowired
    private ArticleService articleService;


    @GetMapping("/list")
    public List<Article> test(){
        return articleService.list();
    }

}

