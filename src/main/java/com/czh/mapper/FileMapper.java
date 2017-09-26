package com.czh.mapper;

import com.czh.entity.FileRouting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {
    @Select("select * from tb_file where id = #{id}")
    FileRouting getFileById(int id);

    @Select("select * from tb_file where uid = #{uid}")
    List<FileRouting> getFileByUid(String uid);

    @Select("select * from tb_file where uid = #{uid} and parsonPath = #{parsonPath}")
    List<FileRouting> getFileByParsonPath(Map<String, String> map);

    @Insert("insert into tb_file(uid, originalFilename, url, md5, status, type) values(#{uid}, #{originalFilename}, #{url}, #{md5}, #{status}, #{type})")
    @SelectKey(statement = "select LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int insertFile(FileRouting book);
}
