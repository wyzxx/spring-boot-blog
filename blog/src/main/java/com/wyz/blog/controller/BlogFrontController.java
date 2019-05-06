package com.wyz.blog.controller;

import com.wyz.blog.dataObject.BlogCategory;
import com.wyz.blog.dataObject.BlogComment;
import com.wyz.blog.entity.Article;
import com.wyz.blog.entity.Category;
import com.wyz.blog.entity.Filter;
import com.wyz.blog.util.BlogUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/5 0:59
 */

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class BlogFrontController extends BlogCommonController{

    @GetMapping("/articles")
    @Override
    public List<Article> getArticles(){
        List<Article> list = super.getArticles();
//        list = BlogUtil.filterList(list);
        return list;
    }


    @GetMapping("/articles/{id}")
    @Override
    public Article getArticle(@PathVariable Integer id){
        Article article = super.getArticle(id);
        if(article.getBlogArticleInfo().getIsEffective()==true) {
            return article;
        }
        return null;
    }


    @Override
    public List<BlogComment> getComments(Integer articleId) {
        List<BlogComment> list = super.getComments(articleId);
//        list = BlogUtil.filterList(list);
        return list;
    }

    @GetMapping("/categories")
    @Override
    public List<Filter> getCategories(){
        List<Filter> list = super.getCategories();
        list = BlogUtil.filterList(list);
        return list;
    }

    @GetMapping("/categories/{id}/articles")
    @Override
    public List<Filter> getArticles(@PathVariable Integer id){
        List<Filter> list = super.getArticles(id);
        list = BlogUtil.filterList(list);
        return list;
    }
}
