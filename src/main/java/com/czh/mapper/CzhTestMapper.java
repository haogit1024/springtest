package com.czh.mapper;

import com.czh.entity.CzhTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CzhTestMapper {
    @Select("select * from tb_czhtest")
    List<CzhTest> getCzhTest();
}
