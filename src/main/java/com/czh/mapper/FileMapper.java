package com.czh.mapper;

import com.czh.entity.FileRouting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("select * from tb_file where id = #{id}")
    FileRouting getFileById(int id);

    @Select("select * from tb_file where uid = #{uid}")
    List<FileRouting> getFileByUid(String uid);

    @Insert("insert into tb_file(uid, originalFilename, url, md5) values(#{uid}, #{originalFilename}, #{url}, #{md5})")
    @SelectKey(statement = "select LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int insertFile(FileRouting book);
}
