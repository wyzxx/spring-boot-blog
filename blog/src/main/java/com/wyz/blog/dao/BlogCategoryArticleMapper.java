package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogCategoryArticle;
import org.apache.ibatis.annotations.Param;

public interface BlogCategoryArticleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_category_article
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_category_article
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    int insert(BlogCategoryArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_category_article
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    int insertSelective(BlogCategoryArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_category_article
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    BlogCategoryArticle selectByPrimaryKey(Integer id);

    BlogCategoryArticle[] selectByArticleId(Integer articleId);

    BlogCategoryArticle[] selectByCategoryId(Integer categoryId);


    BlogCategoryArticle selectByCategoryAndArticle(@Param("categoryId") Integer categoryId,@Param("articleId") Integer articleId);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_category_article
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    int updateByPrimaryKeySelective(BlogCategoryArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_category_article
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    int updateByPrimaryKey(BlogCategoryArticle record);

}