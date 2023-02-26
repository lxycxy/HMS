package com.back.backend.Mapper;

import com.back.backend.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * FROM USER")
    List<User> getAll();

    @Delete("DELETE FROM USER WHERE id = #{id}")
    void deleteUser(Integer id);

    @Insert("insert into user(id, name, password, role, tel, age, sex, address, time) " +
            "values(#{id},#{name},#{password},#{role},#{tel},#{age},#{sex},#{address},#{time})")
    void addUser(User user);

    @Update("update user set name = #{name},  password = #{password}, role = #{role}, tel = #{tel}, age = #{age}," +
            " sex = #{sex}, address = #{address} where id = #{id}")

    void updateUser(User user);
}
