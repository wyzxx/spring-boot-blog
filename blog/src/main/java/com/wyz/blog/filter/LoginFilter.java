package com.wyz.blog.filter;

import com.wyz.blog.controller.BaseController;
import com.wyz.blog.error.BlogError;
import com.wyz.blog.error.BlogException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wyz
 * @Date: 2019/5/8 5:44
 */

@Component
public class LoginFilter extends BaseController implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {


        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
//            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        boolean isLogin = (boolean) request.getSession().getAttribute("ISLOGIN");
        if(isLogin!=true){
//            throw new BlogException(BlogError.PASSWD_WRONG);
            return false;
        }
        return true;
    }
}
