package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogComment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogCommentMapperTest {

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        BlogComment blogComment = new BlogComment();
        blogComment.setContent("很棒!谢谢");
        blogCommentMapper.insert(blogComment);
    }

    @Test
    public void insertSelective() {
        BlogComment blogComment = new BlogComment();
        blogComment.setContent("很棒!谢谢");
        blogCommentMapper.insertSelective(blogComment);
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