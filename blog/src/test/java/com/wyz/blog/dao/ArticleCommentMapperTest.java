package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogArticleComment;
import com.wyz.blog.dataObject.BlogArticleContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleCommentMapperTest {

    @Autowired
    private BlogArticleCommentMapper blogArticleCommentMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        BlogArticleComment blogArticleComment = new BlogArticleComment();
        blogArticleComment.setArticleId(1);
        blogArticleComment.setCommentId(1);
        blogArticleCommentMapper.insert(blogArticleComment);
    }

    @Test
    public void insertSelective() {
        BlogArticleComment blogArticleComment = new BlogArticleComment();
        blogArticleComment.setArticleId(1);
        blogArticleComment.setCommentId(1);
        blogArticleCommentMapper.insertSelective(blogArticleComment);
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
        BlogArticleComment blogArticleComment = blogArticleCommentMapper.selectByPrimaryKey(1);
        blogArticleComment.setCommentId(1);
        blogArticleComment.setModifiedBy(null);
        blogArticleCommentMapper.updateByPrimaryKeySelective(blogArticleComment);
    }

    @Test
    public void updateByPrimaryKey() {
    }
}