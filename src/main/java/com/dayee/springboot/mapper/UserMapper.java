package com.dayee.springboot.mapper;

import com.dayee.springboot.PO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * 访问数据的接口
 */
public interface UserMapper {

    //#{}取值预编译，防止sql注入，${}有注入风险
    @Insert("INSERT INTO t_user_info(name,phone,create_time,age) VALUES(#{name},#{phone},#{createDate},#{age})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")//keyProperty是java对象的属性，kegColumn是数据库字段
    int insert(User user);
}
