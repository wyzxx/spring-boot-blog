package com.wyz.blog.dao;

import com.wyz.blog.dataObject.BlogNotify;

public interface BlogNotifyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_notify
     *
     * @mbg.generated Fri May 03 02:15:48 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_notify
     *
     * @mbg.generated Fri May 03 02:15:48 CST 2019
     */
    int insert(BlogNotify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_notify
     *
     * @mbg.generated Fri May 03 02:15:48 CST 2019
     */
    int insertSelective(BlogNotify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_notify
     *
     * @mbg.generated Fri May 03 02:15:48 CST 2019
     */
    BlogNotify selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_notify
     *
     * @mbg.generated Fri May 03 02:15:48 CST 2019
     */
    int updateByPrimaryKeySelective(BlogNotify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_notify
     *
     * @mbg.generated Fri May 03 02:15:48 CST 2019
     */
    int updateByPrimaryKey(BlogNotify record);
}