package com.wyz.blog.controller;

import com.wyz.blog.dataObject.BlogComment;
import com.wyz.blog.entity.Article;
import com.wyz.blog.entity.Comment;
import com.wyz.blog.entity.Filter;
import com.wyz.blog.entity.Sessions;
import com.wyz.blog.error.BlogException;
import com.wyz.blog.service.BlogArticleService;
import com.wyz.blog.service.BlogCommentService;
import com.wyz.blog.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/4 17:49
 */

@RestController
@RequestMapping(value = "/admin")
//@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class BlogAdminController extends BlogCommonController {


    @Autowired
    private BlogCommentService blogCommentService;

    @Autowired
    private BlogArticleService blogArticleService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/login")
    public Boolean login(@RequestParam(name = "user") String user,
                        @RequestParam(name = "passwd") String passwd,HttpServletRequest request, HttpServletResponse response){
        //验证密码
        //此处未验证

        httpServletRequest.getSession().setAttribute("ISLOGIN",true);
        return true;
    }



    @GetMapping("/articles")
    @Override
    public List<Article> getArticles(){
        List<Article> list =  super.getArticles();
        return list;
    }

    @GetMapping("/articles/{id}")
    @Override
    public Article getArticle(@PathVariable Integer id){
        Article article = super.getArticle(id);
        return article;
    }

    @PostMapping("/articles")
    public void addArticle(@RequestParam(name = "title") String title,
                           @RequestParam(name = "category") String category,
                           @RequestParam(name = "isEffective") Boolean isEffective,
                           @RequestParam(name = "imgUrl") String imgUrl,
                           @RequestParam(name = "data") String data) throws BlogException {


        blogArticleService.addArticle(title,category,isEffective,imgUrl,data);

    }

    @PutMapping("/articles/{id}")
    public void updateArticle(@RequestParam(name = "title") String title,
                              @RequestParam(name = "isCategoryChanged") Boolean isCategoryChanged,
                              @RequestParam(name = "category") String category,
                              @RequestParam(name = "isEffective") Boolean isEffective,
                              @RequestParam(name = "imgUrl") String imgUrl,
                              @RequestParam(name = "data") String data,@PathVariable Integer id) throws BlogException {

        System.out.println(title+" "+category+" "+isEffective+" "+imgUrl+" "+ data+" "+id);

        // 更新文章
        blogArticleService.updateArticle(id,title,isCategoryChanged,category,isEffective,imgUrl,data);

    }

    @DeleteMapping("/articles/{id}")
    public boolean delArticle(@PathVariable Integer id){



        boolean result = blogArticleService.delArticle(id);
        return result;
    }


    @GetMapping("/comments")
    @Override
    public List<BlogComment> getComments(){
        List<BlogComment> list = super.getComments();
        return list;
    }

    @GetMapping("/articles/{articleId}/comments")
    @Override
    public List<Comment> getComments(@PathVariable Integer articleId){
        List<Comment> list = super.getComments(articleId);
        return list;
    }


    @PutMapping("/comments/{id}")
    public void updateComment(
                              @RequestParam(name = "isEffective") Boolean isEffective,
                              @PathVariable Integer id) throws BlogException {


        System.out.println(isEffective+" "+id);

        blogCommentService.updateComment(id,isEffective);

    }

    @DeleteMapping("/comments/{id}")
    public boolean delComment(@PathVariable Integer id){



        boolean result = blogCommentService.deleteComment(id);
        return result;
    }



    @PutMapping("/content/{id}")
    public boolean updateContent(@RequestParam(name = "data") String data,
                                 @PathVariable Integer id){

        blogArticleService.updateContent(id,data);

        return true;
    }


}


