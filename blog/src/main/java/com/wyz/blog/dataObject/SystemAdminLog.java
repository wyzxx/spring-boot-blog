package com.wyz.blog.dataObject;

import java.util.Date;

public class SystemAdminLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_admin_log.id
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_admin_log.ip
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private String ip;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_admin_log.time
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private Date time;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_admin_log.operation_url
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    private String operationUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_admin_log.id
     *
     * @return the value of sys_admin_log.id
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_admin_log.id
     *
     * @param id the value for sys_admin_log.id
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_admin_log.ip
     *
     * @return the value of sys_admin_log.ip
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_admin_log.ip
     *
     * @param ip the value for sys_admin_log.ip
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_admin_log.time
     *
     * @return the value of sys_admin_log.time
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_admin_log.time
     *
     * @param time the value for sys_admin_log.time
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_admin_log.operation_url
     *
     * @return the value of sys_admin_log.operation_url
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public String getOperationUrl() {
        return operationUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_admin_log.operation_url
     *
     * @param operationUrl the value for sys_admin_log.operation_url
     *
     * @mbg.generated Fri May 03 00:46:10 CST 2019
     */
    public void setOperationUrl(String operationUrl) {
        this.operationUrl = operationUrl == null ? null : operationUrl.trim();
    }
}