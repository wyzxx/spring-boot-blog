package com.wyz.blog.service;

import com.wyz.blog.dataObject.Name;
import com.wyz.blog.dao.NameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    @Autowired
    private NameDao nameDao;

    public String sayHello(){
        Name name = nameDao.findByName("wyz");
        return name.getName();
    }
}
