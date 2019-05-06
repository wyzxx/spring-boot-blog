package com.wyz.blog.controller;

import com.wyz.blog.dataObject.BlogCategory;
import com.wyz.blog.dataObject.BlogComment;
import com.wyz.blog.entity.Article;
import com.wyz.blog.entity.Filter;
import com.wyz.blog.service.BlogArticleService;
import com.wyz.blog.service.BlogCategoryService;
import com.wyz.blog.service.impl.BlogCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/5 0:56
 */
@RestController
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class BlogCommonController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private BlogArticleService blogArticleService;

    @Autowired
    private BlogCommentServiceImpl blogCommentService;

    public List<Article> getArticles(){
        List<Article> list =  blogArticleService.getArticles();
        return list;
    }

    public List<Filter> getArticles(Integer categoryId){
        List<Filter> list =  blogArticleService.getArticles(categoryId);
        return list;
    }

    public Article getArticle(Integer id){
        Article article = blogArticleService.getArticle(id);
        return article;
    }

    public boolean addComment(Integer articleId,String content,String name,String email,Integer ref){
        boolean res = blogCommentService.addComment(articleId,content,name,email,ref);
        return res;
    }

    public List<BlogComment> getComments(){
        List<BlogComment> list = blogCommentService.getComments();
        return list;
    }


    public List<Filter> getComments(Integer articleId) {
        List<Filter> list = blogCommentService.getComments(articleId);
        return list;
    }

    public List<Filter> getCategories(){
        List<Filter> list = blogCategoryService.getCategories();
        return list;
    }

}
