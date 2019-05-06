package com.wyz.blog.dao;

import com.wyz.blog.dataObject.SystemVisit;
import org.apache.ibatis.jdbc.Null;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemVisitMapperTest {

    @Autowired
    private SystemVisitMapper systemVisitMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        SystemVisit systemVisit = new SystemVisit();
        systemVisit.setIp("127.0.0.1");
        systemVisit.setTime(new Date());
        systemVisitMapper.insert(systemVisit);
//        systemVisit.setTime(new Date());
    }

    @Test
    public void insertSelective() {
        SystemVisit systemVisit = new SystemVisit();
        systemVisit.setIp("127.0.0.1");
//        systemVisit.setTime(new Date());
//        systemVisit.setTime(null);
        systemVisitMapper.insertSelective(systemVisit);
    }

    @Test
    public void selectByPrimaryKey() {
        SystemVisit systemVisit = systemVisitMapper.selectByPrimaryKey(1);
        System.out.println(systemVisit.getId());
        System.out.println(systemVisit.getIp());
        System.out.println(systemVisit.getTime());
    }

}