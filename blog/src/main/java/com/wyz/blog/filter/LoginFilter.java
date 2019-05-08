package com.wyz.blog.filter;

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
        request.getSession().setAttribute("isLogin",true);
        System.out.println(request);
        System.out.println(response);
        return true;
    }
}
