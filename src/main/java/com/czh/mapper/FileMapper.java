package com.czh.mapper;

import com.czh.entity.File;
import com.czh.entity.FileRouting;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {
    @Select("select * from tb_file where id = #{id} and status = 1")
    File getFileById(int id);

    @Select("select * from tb_file where uid = #{uid}")
    List<File> getFileByUid(int uid);

    @Select("select * from tb_file where uid = #{uid} and parsonPath = #{parsonPath} and status = 1")
    List<File> getFileByParsonPath(Map<String, String> map);

    @Select("select * from tb_file where uid = #{uid} and parsonId = #{parsonId} and status = 1")
    List<File> getFileByParsonId(Map<String, Object> map);

    @Insert("insert into tb_file(uid, filename, url, md5, status, type, size, parsonPath, parsonId) values(#{uid}, #{filename}, #{url}, #{md5}, #{status}, #{type}, #{size}, #{parsonPath}, #{parsonId})")
    @SelectKey(statement = "select LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int insertFile(File file);

    @Update("update tb_file set uid = #{uid}, filename = #{filename}, url = #{url}, md5 = #{md5}, status = #{status}, type = #{type}, size = #{size}, parsonPath = #{parsonPath}, parsonId = #{parsonId} where id = #{id}")
    int updateFile(File file);
}
