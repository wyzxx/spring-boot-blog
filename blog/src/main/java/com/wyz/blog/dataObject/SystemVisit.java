package com.wyz.blog.dataObject;

import java.util.Date;

public class SystemVisit {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_visit.id
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_visit.ip
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private String ip;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_visit.time
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private Date time;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_visit.id
     *
     * @return the value of sys_visit.id
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_visit.id
     *
     * @param id the value for sys_visit.id
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_visit.ip
     *
     * @return the value of sys_visit.ip
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_visit.ip
     *
     * @param ip the value for sys_visit.ip
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_visit.time
     *
     * @return the value of sys_visit.time
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_visit.time
     *
     * @param time the value for sys_visit.time
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setTime(Date time) {
        this.time = time;
    }
}