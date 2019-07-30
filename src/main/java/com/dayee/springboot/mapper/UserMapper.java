package com.dayee.springboot.mapper;

import com.dayee.springboot.PO.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Insert("INSERT INTO t_user_info(name,age,create_time,phone) VALUES(#{name},#{age},#{create_time},#{phone})")
    @Options(useGeneratedKeys =true,keyProperty = "id",keyColumn = "id")
    int insert(User user);

    @Select("SELECT * FROM t_user_info")
    @Results({
            @Result(column = "create_time",property = "create_time")
    })
    List<User> getAll();

    @Select("SELECT * FROM t_user_info WHERE id = #{id}")
    @Results({
            @Result(column = "create_time",property = "create_time")
    })
    User findById(long id);

    @Update("UPDATE t_user_info SET name=#{name} WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM t_user_info WHERE id =#{id}")
    void delete(long userId);
}
