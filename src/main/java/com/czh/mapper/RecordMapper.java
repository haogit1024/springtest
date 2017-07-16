package com.czh.mapper;

import com.czh.entity.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by czh on 17-7-15.
 */
@Mapper
public interface RecordMapper {
    @Select("select * from tb_record where id = #{id}")
    Record getRecord(@Param("id") int id);

    @Select("select * from tb_record where tid = #{tid}")
    List<Record> getRecordByTid(@Param("tid") int tid);

    @Insert("insert into tb_record(tid,createTime, time, grade, money) values(#{tid}, #{createTime}, #{time}, #{grade}, #{money})")
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    int insertRecord(Record record);

    @Update("update tb_record set tid = #{tid}, createTime = #{createTime}, time = #{time}, grade = #{grade}, money = #{money} where id = #{id}")
    int updateRecord(Record record);
}
