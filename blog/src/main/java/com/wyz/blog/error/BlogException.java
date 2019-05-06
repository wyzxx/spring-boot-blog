package com.wyz.blog.error;

/**
 * @Author: wyz
 * @Date: 2019/5/4 0:26
 */
public class BlogException extends Exception implements CommonError {

    private CommonError commonError;

    public BlogException(CommonError commonError) {
        this.commonError = commonError;
    }
    public BlogException(CommonError commonError,String errMsg){
        this.commonError = commonError;
        this.commonError.setErrorMsg(errMsg);
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
        this.commonError.setErrorMsg(errorMsg);
    }


}
