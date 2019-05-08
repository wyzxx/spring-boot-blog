package com.wyz.blog.filter;

import com.wyz.blog.error.BlogError;
import com.wyz.blog.error.BlogException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wyz
 * @Date: 2019/5/8 5:44
 */

@Component
public class LoginFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean isLogin = (boolean) request.getSession().getAttribute("ISLOGIN");
        if(isLogin!=true){
            throw new BlogException(BlogError.PASSWD_WRONG);
        }
        return true;
    }
}
