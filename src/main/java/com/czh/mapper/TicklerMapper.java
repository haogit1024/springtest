package com.czh.mapper;

import com.czh.entity.Tickler;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by czh on 17-7-4.
 */
@Mapper
public interface TicklerMapper {

    //select last_insert_id()是mysql的函数，用户获得刚刚插入数据的ID
    @Insert("insert into tb_tickler(bid, inputTime, time, remark, status) value(#{bid}, #{inputTime}, #{time}, #{remark}, #{status})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    int insertTickler(@Param("tickler") Tickler tickler);

    @Select("select * from tb_tickler where id = #{id}")
    Tickler getTicklerById(@Param("id") int id);

    @Select("select * from tb_tickler where bid = #{bid}")
    List<Tickler> getTicklerByBid(@Param("bid") int bid);

    @Update("update tb_tickler set bid = #{bid}, inputTime = #{inputTime}, time = #{time}, remark = #{remark}, status = #{status} where id = #{id}")
    int updateTickler(Tickler tickler);
}
