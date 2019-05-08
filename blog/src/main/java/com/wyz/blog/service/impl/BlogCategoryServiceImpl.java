package com.wyz.blog.service.impl;

import com.wyz.blog.dao.BlogCategoryArticleMapper;
import com.wyz.blog.dao.BlogCategoryMapper;
import com.wyz.blog.dataObject.BlogCategory;
import com.wyz.blog.dataObject.BlogCategoryArticle;
import com.wyz.blog.entity.Category;
import com.wyz.blog.entity.Filter;
import com.wyz.blog.error.BlogError;
import com.wyz.blog.error.BlogException;
import com.wyz.blog.service.BlogCategoryService;
import com.wyz.blog.util.BlogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/4 0:40
 */

@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Autowired
    private BlogCategoryArticleMapper blogCategoryArticleMapper;


    @Override
    public BlogCategory isExist(String category) {
        BlogCategory blogCategory = blogCategoryMapper.selectByName(category);
        return blogCategory;
    }

    @Override
    public BlogCategory addCategory(String category) throws BlogException {
        BlogCategory blogCategory = isExist(category);
        if(blogCategory!=null){
            throw new BlogException(BlogError.CATEGORY_ALLREADY_EXIST);
        }
        BlogCategory blogCategoryNew = new BlogCategory();
        blogCategoryNew.setName(category);
        blogCategoryMapper.insertSelective(blogCategoryNew);
        return blogCategoryMapper.selectByName(category);
    }

    @Override
    public boolean delCategory(String category) throws BlogException {
        BlogCategory blogCategory = isExist(category);
        if(blogCategory == null){
            throw new BlogException(BlogError.CATEGORY_NOT_EXIST);
        }

        int result = blogCategoryMapper.deleteByPrimaryKey(blogCategory.getId());

        return result>0;
    }

    @Override
    public List<String> getCategory(Integer articleId) {
        BlogCategoryArticle[] blogCategoryArticles = blogCategoryArticleMapper.selectByArticleId(articleId);
        List<String> list = new ArrayList<>();
        for (BlogCategoryArticle k:blogCategoryArticles) {
           String category =  blogCategoryMapper.selectByPrimaryKey(k.getCategoryId()).getName();
            list.add(category);
        }
        return list;
    }

    @Override
    public List<Category> getCategories() {
        BlogCategory[] blogCategories = blogCategoryMapper.selectAll();
        if(blogCategories==null){
            return null;
        }
        Category[] categories = BlogUtil.convertFromBlogCategory(blogCategories);
        List<Category> list = new ArrayList<>();
        for (Category k:categories) {
            list.add(k);
        }

        return list;
    }
}
