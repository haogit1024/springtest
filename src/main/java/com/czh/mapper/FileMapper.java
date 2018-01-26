package com.czh.mapper;

import com.czh.entity.FileEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileMapper {
    @Select("select * from tb_file where id = #{id} and status = 1")
    FileEntity getFileById(int id);

    @Select("select * from tb_file where uid = #{uid} and status = 1")
    List<FileEntity> getFileByUid(int uid);

    @Select("select * from tb_file where uid = #{uid} and parsonPath = #{parsonPath} and status = 1")
    List<FileEntity> getFileByParsonPath(Map<String, String> map);

    @Select("select * from tb_file where uid = #{uid} and parsonId = #{parsonId} and status = 1")
    List<FileEntity> getFileByParsonId(Map<String, Object> map);

    @Insert("insert into tb_file(uid, filename, realname,url, md5, status, type, size, parsonPath, parsonId, time) values(#{uid}, #{filename}, #{realname},#{url}, #{md5}, #{status}, #{type}, #{size}, #{parsonPath}, #{parsonId}, #{time})")
    @SelectKey(statement = "select LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int insertFile(FileEntity file);

    @Update("update tb_file set uid = #{uid}, filename = #{filename}, url = #{url}, md5 = #{md5}, status = #{status}, type = #{type}, size = #{size}, parsonPath = #{parsonPath}, parsonId = #{parsonId}, time = #{time} where id = #{id}")
    int updateFile(FileEntity file);
}
