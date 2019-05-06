package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogNotify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogNotifyMapperTest {

    @Autowired
    private BlogNotifyMapper blogNotifyMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        BlogNotify blogNotify = new BlogNotify();
        blogNotify.setEmail("11@qq.com");
        blogNotifyMapper.insert(blogNotify);
    }

    @Test
    public void insertSelective() {

        BlogNotify blogNotify = new BlogNotify();
        blogNotify.setEmail("11@qq.com");
        blogNotifyMapper.insertSelective(blogNotify);
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