package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogCategoryMapperTest {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setName("2019");
        blogCategoryMapper.insert(blogCategory);
    }

    @Test
    public void insertSelective() {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setName("2019");
        blogCategoryMapper.insertSelective(blogCategory);
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