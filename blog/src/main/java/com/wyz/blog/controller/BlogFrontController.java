package com.wyz.blog.controller;

import com.wyz.blog.entity.Article;
import com.wyz.blog.entity.Category;
import com.wyz.blog.entity.Comment;
import com.wyz.blog.entity.Filter;
import com.wyz.blog.util.BlogUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/5 0:59
 */


// TODO: 2019/5/8 返回统一的接口

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class BlogFrontController extends BlogCommonController{

    @GetMapping("/articles")
    @Override
    public List<Article> getArticles(){
        List<Article> list = super.getArticles();
        list = BlogUtil.filterList(list);
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

    @PostMapping("/articles/{articleId}/comments")
    @Override
    public boolean addComment(@PathVariable Integer articleId,
                              @RequestParam(name = "content") String content,
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "reference") Integer ref
                              ){
        boolean res = super.addComment(articleId,content,name,email,ref);
        return res;
    }

    @GetMapping("/articles/{articleId}/comments")
    @Override
    public List<Comment> getComments(@PathVariable  Integer articleId) {
        List<Comment> list = super.getComments(articleId);
        list = BlogUtil.filterList(list);
        return list;
    }

    @GetMapping("/categories")
    @Override
    public List<Category> getCategories(){
        List<Category> list = super.getCategories();
        list = BlogUtil.filterList(list);
        return list;
    }

    @GetMapping("/categories/{id}/articles")
    @Override
    public List<Article> getArticles(@PathVariable Integer id){
        List<Article> list = super.getArticles(id);
        list = BlogUtil.filterList(list);
        return list;
    }
}
