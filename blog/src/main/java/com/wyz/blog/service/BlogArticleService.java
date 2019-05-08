package com.wyz.blog.service;

import com.wyz.blog.entity.Article;
import com.wyz.blog.error.BlogException;

import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/4 1:12
 */
public interface BlogArticleService {

    boolean addArticle(String title, String category, Boolean isEffective, String imgUrl, String data) throws BlogException;

    boolean updateArticle(Integer id, String title, Boolean isCategoryChange, String category, Boolean isEffective, String imgUrl, String data) throws BlogException;

    List<Article> getArticles();

    Article getArticle(Integer id);

    boolean delArticle(Integer id);

    List<Article> getArticles(Integer categoryId);

    boolean updateContent(Integer id, String data);
}
