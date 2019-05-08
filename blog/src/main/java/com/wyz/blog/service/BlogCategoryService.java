package com.wyz.blog.service;

import com.wyz.blog.dataObject.BlogCategory;
import com.wyz.blog.entity.Category;
import com.wyz.blog.entity.Filter;
import com.wyz.blog.error.BlogException;

import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/4 0:38
 */
public interface BlogCategoryService {
    BlogCategory isExist(String category);
    BlogCategory addCategory(String category) throws BlogException;
    boolean delCategory(String category) throws BlogException;
    List<String> getCategory(Integer articleId);

    List<Category> getCategories();
}
