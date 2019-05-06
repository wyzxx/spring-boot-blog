package com.wyz.blog.dao;

import com.wyz.blog.dataObject.Name;
import org.apache.ibatis.annotations.Param;

public interface NameDao {

//    List<Name> list

    Name findByName(@Param("name") String name);

}
