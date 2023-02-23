package com.back.backend.Mapper;

import com.back.backend.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * FROM USER")
    List<User> getAll();

    @Delete("DELETE FROM USER WHERE id = #{id}")
    void deleteUser(Integer id);
}
