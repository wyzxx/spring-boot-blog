package com.wyz.blog.dao;

import com.wyz.blog.dataObject.SystemAdminLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemAdminLogMapperTest {

    @Autowired
    private SystemAdminLogMapper systemAdminLogMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        SystemAdminLog systemAdminLog = new SystemAdminLog();
        systemAdminLog.setIp("127.0.0.1");
        systemAdminLog.setTime(new Date());
        systemAdminLog.setOperationUrl("localhost/admin/article/delete/1");
        systemAdminLogMapper.insert(systemAdminLog);
    }

    @Test
    public void insertSelective() {
        SystemAdminLog systemAdminLog = new SystemAdminLog();
        systemAdminLog.setIp("127.0.0.1");
        systemAdminLog.setOperationUrl("localhost/admin/article/delete/1");
        systemAdminLogMapper.insertSelective(systemAdminLog);
    }

    @Test
    public void selectByPrimaryKey() {
    }


}