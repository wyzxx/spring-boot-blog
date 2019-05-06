package com.wyz.blog.error;

/**
 * @Author: wyz
 * @Date: 2019/5/4 0:23
 */
public interface CommonError {
    int getErrorCode();
    String getErrorMsg();
    void setErrorMsg(String errorMsg);
}
