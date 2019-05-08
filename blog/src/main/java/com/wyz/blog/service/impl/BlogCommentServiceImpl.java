package com.wyz.blog.service.impl;

import com.wyz.blog.dao.BlogArticleCommentMapper;
import com.wyz.blog.dao.BlogCommentMapper;
import com.wyz.blog.dataObject.BlogArticleComment;
import com.wyz.blog.dataObject.BlogComment;
import com.wyz.blog.entity.Comment;
import com.wyz.blog.service.BlogCommentService;
import com.wyz.blog.util.BlogUtil;
import com.wyz.blog.validator.BlogValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/5 1:24
 */
@Service
public class BlogCommentServiceImpl implements BlogCommentService {

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Autowired
    private BlogArticleCommentMapper blogArticleCommentMapper;


    @Override
    @Transactional
    public boolean addComment(Integer articleId,String content, String name, String email, Integer ref) {
        BlogComment blogComment = BlogUtil.createBlogComment(content,name,email,ref);
        BlogValidator.validateComment(blogComment);

        if(ref==0){
            // to do  说明没有引用
        }
        int res1 = blogCommentMapper.insertSelective(blogComment);
        BlogArticleComment blogArticleComment = new BlogArticleComment();
        blogArticleComment.setArticleId(articleId);
        blogArticleComment.setCommentId(blogComment.getId());
        int res2 = blogArticleCommentMapper.insertSelective(blogArticleComment);
        //to do
        return res1>0&res2>0;
    }

    @Override
    @Transactional
    public boolean deleteComment(Integer id) {
        BlogComment blogComment = blogCommentMapper.selectByPrimaryKey(id);
        if(blogComment==null){
            return false;
        }
        BlogArticleComment[] blogArticleComments = blogArticleCommentMapper.selectByCommentId(id);
        for (BlogArticleComment k:blogArticleComments) {
            blogArticleCommentMapper.deleteByPrimaryKey(k.getId());
        }
        blogCommentMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateComment(Integer id, Boolean isEffective) {
        BlogComment blogComment  = new BlogComment();
        blogComment.setId(id);
        blogComment.setIsEffective(isEffective);
        int res = blogCommentMapper.updateByPrimaryKeySelective(blogComment);
        return  res>0;
    }

    @Override
    public List<BlogComment> getComments() {
        BlogComment[] blogComments = blogCommentMapper.selectAll();
        List<BlogComment> list =  BlogUtil.createCommentList(blogComments);
        return list;
    }

    @Override
    public List<Comment> getComments(Integer articleId) {
        BlogArticleComment[] blogArticleComments = blogArticleCommentMapper.selectByArticleID(articleId);
        if(blogArticleComments==null){
            return null;
        }
        List<Comment> list = new ArrayList<>();
        for (BlogArticleComment k:blogArticleComments) {
            BlogComment blogComment = blogCommentMapper.selectByPrimaryKey(k.getCommentId());
            Comment comment = BlogUtil.convertFromBlogComment(blogComment);
            list.add(comment);
        }
        return list;
    }
}
