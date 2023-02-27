package com.back.backend.Mapper;

import com.back.backend.pojo.Drugs;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DrugsMapper {
    @Select("select * FROM drugs")
    List<Drugs> getAll();

    @Delete("DELETE FROM drugs WHERE id = #{id}")
    void deleteDrugs(Integer id);

    @Update("update drugs set name = #{name}, price = #{price}, purchasePrice = #{purchasePrice}, num = #{num}, introduceDate = #{introduceDate}," +
            "productDate = #{productDate},qualityPeriod = #{qualityPeriod},supplyUnit = #{supplyUnit}," +
            "productUnit = #{productUnit} where id = #{id}")
    void updateDrugs(Drugs drugs);

    @Insert("insert into drugs(id, name, price, purchasePrice, num, introduceDate, productDate, qualityPeriod, supplyUnit, productUnit)" +
            "value(#{id}, #{name}, #{price}, #{purchasePrice}, #{num}, #{introduceDate}, #{productDate}, #{qualityPeriod}, #{supplyUnit}, #{productUnit})")
    void addDrugs(Drugs drugs);
}
