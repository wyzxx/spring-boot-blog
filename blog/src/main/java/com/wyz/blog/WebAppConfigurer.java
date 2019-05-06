package com.wyz.blog;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: wyz
 * @Date: 2019/5/5 19:54
 */
//@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
//                .allowedOrigins("http://192.168.1.97")
//                .allowedMethods("GET", "POST")
//                .allowCredentials(false).maxAge(3600);
    }
}
