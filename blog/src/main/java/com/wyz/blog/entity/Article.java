package com.wyz.blog.entity;

import com.wyz.blog.dataObject.BlogArticleContent;
import com.wyz.blog.dataObject.BlogArticleInfo;

import java.util.List;

/**
 * @Author: wyz
 * @Date: 2019/5/4 1:20
 */
public class Article implements Filter {
    private BlogArticleInfo blogArticleInfo;
    private List<String> category;
    private List<String> comment;
    private BlogArticleContent blogArticleContent;
//    private List<BlogCategoryArticle> list = new ArrayList<>();

    public Article(){
        super();
    }

    public Article(BlogArticleInfo blogArticleInfo) {
        this.blogArticleInfo = blogArticleInfo;
    }

    public BlogArticleInfo getBlogArticleInfo() {
        return blogArticleInfo;
    }

    public void setBlogArticleInfo(BlogArticleInfo blogArticleInfo) {
        this.blogArticleInfo = blogArticleInfo;
    }

    public BlogArticleContent getBlogArticleContent() {
        return blogArticleContent;
    }

    public void setBlogArticleContent(BlogArticleContent blogArticleContent) {
        this.blogArticleContent = blogArticleContent;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    @Override
    public Boolean getIsEffective() {
        return blogArticleInfo.getIsEffective();
    }
}
