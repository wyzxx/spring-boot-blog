package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogCategoryArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogCategoryArticleMapperTest {

    @Autowired
    BlogCategoryArticleMapper blogCategoryArticleMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        BlogCategoryArticle blogCategoryArticle = new BlogCategoryArticle();
        blogCategoryArticle.setArticleId(1);
        blogCategoryArticle.setCategoryId(1);
        blogCategoryArticleMapper.insert(blogCategoryArticle);
    }

    @Test
    public void insertSelective() {
        BlogCategoryArticle blogCategoryArticle = new BlogCategoryArticle();
        blogCategoryArticle.setArticleId(1);
        blogCategoryArticle.setCategoryId(1);
        blogCategoryArticleMapper.insertSelective(blogCategoryArticle);
    }

    @Test
    public void selectByCAA(){
        BlogCategoryArticle blogCategoryArticle = blogCategoryArticleMapper.selectByCategoryAndArticle(1,4);
        System.out.println(1);
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}