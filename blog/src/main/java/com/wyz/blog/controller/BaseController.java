package com.wyz.blog.controller;

import com.wyz.blog.error.BlogError;
import com.wyz.blog.error.BlogException;
import com.wyz.blog.response.CommonReturn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wyz
 * @Date: 2019/5/8 16:26
 */
public class BaseController {


    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String,Object> map = new HashMap<>();
        if(ex instanceof BlogException){
            BlogException blogException = (BlogException) ex;
            map.put("errCode",blogException.getErrorCode());
            map.put("errMsg",blogException.getErrorMsg());
        }
        else {
            map.put("errCode",BlogError.UNKNOWN_ERROR.getErrorCode());
            map.put("errMsg",BlogError.UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonReturn.create(map,"fail");
    }
}
