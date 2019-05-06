package com.wyz.blog.service;

import com.wyz.blog.dataObject.BlogComment;
import com.wyz.blog.entity.Filter;

import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/5 1:21
 */
public interface BlogCommentService {


    boolean addComment(Integer articleId,String content, String name, String email, Integer ref);
    boolean deleteComment(Integer id);
    boolean updateComment(Integer id, Boolean isEffective);
    List<BlogComment> getComments();
    List<Filter> getComments(Integer articleId);
}
