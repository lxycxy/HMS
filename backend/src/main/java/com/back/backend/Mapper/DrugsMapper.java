package com.back.backend.Mapper;

import com.back.backend.pojo.Drugs;
import com.back.backend.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DrugsMapper {
    @Select("select * FROM drugs")
    List<Drugs> getAll();

    @Delete("DELETE FROM drugs WHERE id = #{id}")
    void deleteDrugs(Integer id);
}
