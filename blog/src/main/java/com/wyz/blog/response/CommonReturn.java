package com.wyz.blog.response;

import com.wyz.blog.entity.Comment;

import java.util.Map;

/**
 * @Author: wyz
 * @Date: 2019/5/8 16:32
 */
public class CommonReturn {

    private String status;
    private Object obj;

    public static CommonReturn create(Object obj){
        return create(obj,"success");
    }

    public static CommonReturn create(Object obj, String status) {
        CommonReturn commonReturn = new CommonReturn();
        commonReturn.setStatus(status);
        commonReturn.setObj(obj);
        return commonReturn;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
