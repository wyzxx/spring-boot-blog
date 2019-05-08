package com.wyz.blog.service.impl;

import com.wyz.blog.dao.BlogArticleContentMapper;
import com.wyz.blog.dao.BlogArticleInfoMapper;
import com.wyz.blog.dao.BlogCategoryArticleMapper;
import com.wyz.blog.dataObject.BlogArticleContent;
import com.wyz.blog.dataObject.BlogArticleInfo;
import com.wyz.blog.dataObject.BlogCategory;
import com.wyz.blog.dataObject.BlogCategoryArticle;
import com.wyz.blog.entity.Article;
import com.wyz.blog.entity.Filter;
import com.wyz.blog.error.BlogError;
import com.wyz.blog.error.BlogException;
import com.wyz.blog.service.BlogArticleService;
import com.wyz.blog.service.BlogCategoryService;
import com.wyz.blog.util.BlogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: wyz
 * @Date: 2019/5/4 1:14
 */

@Service
public class BlogArticleServiceImpl implements BlogArticleService {




    @Autowired
    private BlogCategoryArticleMapper blogCategoryArticleMapper;


    @Autowired
    private BlogCategoryService blogCategoryService;

    @Autowired
    private BlogArticleInfoMapper blogArticleInfoMapper;


    @Autowired
    private BlogArticleContentMapper blogArticleContentMapper;


    @Override
    @Transactional
    public boolean addArticle(String title, String category, Boolean isEffective, String imgUrl, String data) throws BlogException {

        BlogArticleInfo blogArticleInfo = BlogUtil.createArticleInfo(title,category,isEffective,imgUrl);

        BlogCategory blogCategory = null;
        //1.创建分类
        if(category!="" & !category.equals("")) {
            blogCategory = checkAndAdd(category);
        }

        //2.内容
        BlogArticleContent blogArticleContent = BlogUtil.createArticleContent(data);

        int resultContent = blogArticleContentMapper.insertSelective(blogArticleContent);

        blogArticleInfo.setContentId(blogArticleContent.getId());

        //3.创建文章 1.信息

        int result = blogArticleInfoMapper.insertSelective(blogArticleInfo);



        //登记到分类文章表中
        int resultCategoryArticle = 1;
        if(category!="" & !category.equals("")){
            BlogCategoryArticle blogCategoryArticle = BlogUtil.createCategoryArticle(blogCategory.getId(),blogArticleInfo.getId());
            resultCategoryArticle  = blogCategoryArticleMapper.insertSelective(blogCategoryArticle);
        }

        return result>0 & resultContent>0 & resultCategoryArticle>0;
    }

    @Override
    @Transactional
    public boolean updateArticle(Integer id, String title, Boolean isCategoryChange, String category, Boolean isEffective, String imgUrl, String data) throws BlogException {
       //1.  更新文章

        BlogArticleInfo blogArticleInfo = blogArticleInfoMapper.selectByPrimaryKey(id);
        if(blogArticleInfo==null){
            throw new BlogException(BlogError.ARTICLE_NOT_EXIST);
        }
        blogArticleInfo.setTitle(title);
        blogArticleInfo.setIsEffective(isEffective);
        blogArticleInfo.setImgUrl(imgUrl);
        blogArticleInfoMapper.updateByPrimaryKeySelective(blogArticleInfo);
        System.out.println(data);
        //更新内容
        if(data!="" & !data.equals("")){
            BlogArticleContent blogArticleContent = BlogUtil.createArticleContent(data);
            blogArticleContent.setId(blogArticleInfo.getContentId());
            blogArticleContentMapper.updateByPrimaryKeySelective(blogArticleContent);
            System.out.println(1);
        }



        if(isCategoryChange==true) {

            //删除原文章所有分类
            if (category == "") {
                BlogCategoryArticle[] blogCategoryArticles = blogCategoryArticleMapper.selectByArticleId(id);
                for (BlogCategoryArticle k : blogCategoryArticles) {
                    blogCategoryArticleMapper.deleteByPrimaryKey(k.getId());
                }
            }


            //判断分类
            if (category != "") {
                BlogCategory blogCategory = checkAndAdd(category);
                BlogCategoryArticle blogCategoryArticle = blogCategoryArticleMapper.selectByCategoryAndArticle(blogCategory.getId(), id);
                if (blogCategoryArticle == null) {
                    blogCategoryArticle = BlogUtil.createCategoryArticle(blogCategory.getId(), id);
                    blogCategoryArticleMapper.insertSelective(blogCategoryArticle);
                }
            }
        }
        return true;
    }

    @Override
    public List<Article> getArticles() {

        List<Article> list = new ArrayList<>();

        BlogArticleInfo[] blogArticleInfos = blogArticleInfoMapper.selectAll();
        for (BlogArticleInfo k:blogArticleInfos) {
            int id = k.getId();
            Article article = generateArticleFromArticleId(k,id);
            list.add(article);
        }
        return list;

    }

    private Article generateArticleFromArticleId(BlogArticleInfo k, int articleId) {
        Article article = new Article();
        article.setBlogArticleInfo(k);
        List<String> category = blogCategoryService.getCategory(articleId);
        article.setCategory(category);
        return article;
    }

    @Override
    @Transactional
    public Article getArticle(Integer id) {
        BlogArticleInfo blogArticleInfo = blogArticleInfoMapper.selectByPrimaryKey(id);

        //流量加1
        if(blogArticleInfo.getTraffic()==null){
            blogArticleInfo.setTraffic(0);
        }
        blogArticleInfo.setTraffic(blogArticleInfo.getTraffic()+1);
        blogArticleInfoMapper.updateByPrimaryKeySelective(blogArticleInfo);
        //

        BlogArticleContent blogArticleContent = blogArticleContentMapper.selectByPrimaryKey(blogArticleInfo.getContentId());
        List<String> category = blogCategoryService.getCategory(id);
//        List<String>  to do comment
        Article article = BlogUtil.createArticle(blogArticleInfo,category,null,blogArticleContent);
        return article;
    }

    @Override
    @Transactional
    public boolean delArticle(Integer id) {
        BlogArticleInfo blogArticleInfo = blogArticleInfoMapper.selectByPrimaryKey(id);
        int res1 = blogArticleContentMapper.deleteByPrimaryKey(blogArticleInfo.getContentId());
        int res2 = blogArticleInfoMapper.deleteByPrimaryKey(id);

        //to do 删除文章相关的评论
        return res1>0&res2>0;
    }

    @Override
    public List<Article> getArticles(Integer categoryId) {
        BlogCategoryArticle[] blogCategoryArticles = blogCategoryArticleMapper.selectByCategoryId(categoryId);
        if(blogCategoryArticles==null||blogCategoryArticles.length==0){
            return null;
        }
        List<Article> list = new ArrayList<>();

        for (BlogCategoryArticle k:blogCategoryArticles) {
            BlogArticleInfo blogArticleInfo = blogArticleInfoMapper.selectByPrimaryKey(k.getArticleId());
            Article article = new Article(blogArticleInfo);
            list.add(article);
        }
        return list;
    }

    @Override
    public boolean updateContent(Integer id,String data) {
        BlogArticleContent blogArticleContent = blogArticleContentMapper.selectByPrimaryKey(id);
        if(blogArticleContent==null){
            return true;//to do errMsg
        }
        blogArticleContent.setContent(data);
        int res = blogArticleContentMapper.updateByPrimaryKeySelective(blogArticleContent);
        return res>0;
    }

    private List<Article> convertFromArticleInfo(BlogArticleInfo[] blogArticleInfos){
        if(blogArticleInfos==null){
            return null;
        }
        List<Article> list = new ArrayList<>();
        for (BlogArticleInfo k:blogArticleInfos) {
            Article article = new Article(k);
            list.add(article);
        }
        return list;
    }

    private BlogCategory checkAndAdd(String category) throws BlogException {
        BlogCategory blogCategory = null;
        if(category!=""){
            //1.创建分类
            blogCategory = blogCategoryService.isExist(category);
            if(blogCategory == null){
                blogCategory = blogCategoryService.addCategory(category);
            }
        }
        return blogCategory;
    }

}
