package com.wyz.blog.error;

/**
 * @Author: wyz
 * @Date: 2019/5/4 0:31
 */
public enum BlogError implements CommonError {
    USER_NOT_LOGIN(10000,"用户未登陆"),
    CATEGORY_ALLREADY_EXIST(10001,"分类已存在"),
    CATEGORY_NOT_EXIST(10002,"分类不存在"),
    ARTICLE_NOT_EXIST(10003,"文章不存在"),
    PASSWD_WRONG(10004,"用户名或密码错误"),
    UNKNOWN_ERROR(20000,"未知错误"),
    ;


    private BlogError(Integer errCode,String errMsg) {

    }

    @Override
    public int getErrorCode() {
        return 0;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }

    @Override
    public void setErrorMsg(String errorMsg) {

    }
}
