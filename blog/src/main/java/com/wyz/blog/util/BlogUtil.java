package com.wyz.blog.util;

import com.wyz.blog.dataObject.*;
import com.wyz.blog.entity.Article;
import com.wyz.blog.entity.Category;
import com.wyz.blog.entity.Comment;
import com.wyz.blog.entity.Filter;
import com.wyz.blog.validator.BlogValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @Author: wyz
 * @Date: 2019/5/4 1:31
 */
public class BlogUtil {


    public static BlogArticleInfo createArticleInfo(String title,String category,Boolean isEffective,String imgUrl){


        BlogArticleInfo blogArticleInfo = new BlogArticleInfo();
        blogArticleInfo.setTitle(title);
        blogArticleInfo.setIsEffective(isEffective);
        blogArticleInfo.setImgUrl(imgUrl);
        // 2.校验文章信息
        BlogValidator.validateArticleInfo(blogArticleInfo);

        return blogArticleInfo;
    }

    public static BlogArticleContent createArticleContent(String data){
        BlogValidator.validateArticleContent(data);

        BlogArticleContent blogArticleContent = new BlogArticleContent();
        blogArticleContent.setContent(data);

        return blogArticleContent;
    }

    public static BlogCategoryArticle createCategoryArticle(Integer categoryId,Integer articleId){
        BlogCategoryArticle blogCategoryArticle = new BlogCategoryArticle();
        blogCategoryArticle.setArticleId(articleId);
        blogCategoryArticle.setCategoryId(categoryId);
        return blogCategoryArticle;
    }


    public static Article createArticle(BlogArticleInfo blogArticleInfo, List<String> category, List<String> comment, BlogArticleContent blogArticleContent) {
        Article article = new Article();
        article.setBlogArticleInfo(blogArticleInfo);
        article.setBlogArticleContent(blogArticleContent);
        article.setCategory(category);
        article.setComment(comment);
        return article;
    }


    public static List<BlogComment> createCommentList(BlogComment[] blogComments){
        if(blogComments==null){
            return null;
        }
        List<BlogComment> list = new ArrayList<>();
        for (BlogComment k:blogComments) {
            list.add(k);
        }
        return list;
    }

    public static BlogComment createBlogComment(String content, String name, String email, Integer ref) {
        BlogComment blogComment = new BlogComment();
        blogComment.setContent(content);
        if(name!=null & !name.equals("")){
            blogComment.setName(name);
        }
        blogComment.setEmail(email);
        blogComment.setReference(ref);
        return blogComment;
    }

    public static <E>  List<E> filterList(List<E> list){
        if(list==null){
            return null;
        }
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()){
            Filter filter = (Filter) listIterator.next();
            if(filter.getIsEffective()==false){
                listIterator.remove();
            }
        }
        return list;
    }

    public static Category convertFromBlogCategory(BlogCategory blogCategory){
        Category category = new Category();
        category.setCreateBy(blogCategory.getCreateBy());
        category.setName(blogCategory.getName());
        category.setId(blogCategory.getId());
        category.setIsEffective(blogCategory.getIsEffective());
        category.setModifiedBy(blogCategory.getModifiedBy());
        return category;
    }

    public static Comment convertFromBlogComment(BlogComment blogComment){
        Comment comment = new Comment();
        comment.setId(blogComment.getId());
        comment.setContent(blogComment.getContent());
        comment.setEmail(blogComment.getEmail());
        comment.setIsEffective(blogComment.getIsEffective());
        comment.setReference(blogComment.getReference());
        comment.setName(blogComment.getName());
        comment.setIsExpected(blogComment.getIsExpected());
        return comment;
    }



    public static Category[] convertFromBlogCategory(BlogCategory[] blogCategories){
        if(blogCategories==null){
            return null;
        }
        int leng = blogCategories.length;
        Category[] categories = new Category[leng];
        for (int i = 0;i<leng;i++){
            categories[i] = convertFromBlogCategory(blogCategories[i]);
        }
        return categories;
    }
}
