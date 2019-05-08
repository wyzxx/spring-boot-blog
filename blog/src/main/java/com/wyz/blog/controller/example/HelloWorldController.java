package com.wyz.blog.controller.example;

import com.wyz.blog.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping("/")
    public String hello(){
        return helloWorldService.sayHello();
    }
}
