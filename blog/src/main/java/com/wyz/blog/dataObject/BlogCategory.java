package com.wyz.blog.dataObject;

import java.util.Date;

public class BlogCategory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog_category.id
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog_category.name
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog_category.is_effective
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private Boolean isEffective;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog_category.create_by
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private Date createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column blog_category.modified_by
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private Date modifiedBy;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog_category.id
     *
     * @return the value of blog_category.id
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog_category.id
     *
     * @param id the value for blog_category.id
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog_category.name
     *
     * @return the value of blog_category.name
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog_category.name
     *
     * @param name the value for blog_category.name
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog_category.is_effective
     *
     * @return the value of blog_category.is_effective
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public Boolean getIsEffective() {
        return isEffective;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog_category.is_effective
     *
     * @param isEffective the value for blog_category.is_effective
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setIsEffective(Boolean isEffective) {
        this.isEffective = isEffective;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog_category.create_by
     *
     * @return the value of blog_category.create_by
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public Date getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog_category.create_by
     *
     * @param createBy the value for blog_category.create_by
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column blog_category.modified_by
     *
     * @return the value of blog_category.modified_by
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public Date getModifiedBy() {
        return modifiedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column blog_category.modified_by
     *
     * @param modifiedBy the value for blog_category.modified_by
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}