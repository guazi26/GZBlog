package org.guazi26.controller;



import org.guazi26.domain.ResponseResult;
import org.guazi26.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 文章表(Article)表控制层
 *
 * @author makejava
 * @since 2023-09-16 20:55:38
 */
@RestController
@RequestMapping("/article")
public class ArticleController{

    @Autowired
    private ArticleService articleService;


    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){

        //查询热门文章，封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }


    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId){
        return articleService.articleList(pageNum, pageSize, categoryId);
    }


    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable Long id){
        return articleService.getArticleDetail(id);
    }

}

