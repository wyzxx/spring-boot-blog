package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogArticleInfo;

public interface BlogArticleInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_article_info
     *
     * @mbg.generated Sat May 04 15:48:30 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_article_info
     *
     * @mbg.generated Sat May 04 15:48:30 CST 2019
     */
    int insert(BlogArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_article_info
     *
     * @mbg.generated Sat May 04 15:48:30 CST 2019
     */
    int insertSelective(BlogArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_article_info
     *
     * @mbg.generated Sat May 04 15:48:30 CST 2019
     */
    BlogArticleInfo selectByPrimaryKey(Integer id);

    BlogArticleInfo[] selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_article_info
     *
     * @mbg.generated Sat May 04 15:48:30 CST 2019
     */
    int updateByPrimaryKeySelective(BlogArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_article_info
     *
     * @mbg.generated Sat May 04 15:48:30 CST 2019
     */
    int updateByPrimaryKey(BlogArticleInfo record);
}