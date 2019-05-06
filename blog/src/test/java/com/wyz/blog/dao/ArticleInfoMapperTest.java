package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogArticleInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleInfoMapperTest {

    @Autowired
    private BlogArticleInfoMapper blogArticleInfoMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        BlogArticleInfo blogArticleInfo = new BlogArticleInfo();
        blogArticleInfo.setTitle("第一篇文章");
        blogArticleInfo.setImgUrl("http://www.baidu.com");
        blogArticleInfoMapper.insert(blogArticleInfo);
    }

    @Test
    public void insertSelective() {
        BlogArticleInfo blogArticleInfo = new BlogArticleInfo();
        blogArticleInfo.setTitle("第一篇文章");
        blogArticleInfoMapper.insertSelective(blogArticleInfo);
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