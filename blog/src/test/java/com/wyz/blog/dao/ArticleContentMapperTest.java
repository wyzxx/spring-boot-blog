package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogArticleContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleContentMapperTest {

    @Autowired
    private BlogArticleContentMapper blogArticleContentMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        BlogArticleContent blogArticleContent = new BlogArticleContent();
        blogArticleContent.setContent("这是内容");
        blogArticleContentMapper.insert(blogArticleContent);
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
        BlogArticleContent blogArticleContent = blogArticleContentMapper.selectByPrimaryKey(7);
        String s = blogArticleContent.getContent();
        System.out.println(1);
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKeyWithBLOBs() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}