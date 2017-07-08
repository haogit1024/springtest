package com.czh.mapper;

import com.czh.entity.Tickler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by czh on 17-7-4.
 */
@Mapper
public interface TicklerMapper {
    @Insert("insert into tb_tickler(uid, inputTime, time, remark, money, status) value(#{uid}, #{inputTime}, #{time}, #{remark}, #{money}, #{status})")
    int addTickler(Tickler tickler);

    @Select("select * from tb_tickler where id = #{id}")
    Tickler getTickler(@Param("id") int id);

//    @Select("select * from tb_tickler where uid = #{uid}")
//    List<Tickler> getTickler(@Param("uid") String uid);
}
