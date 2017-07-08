package com.czh.mapper;

import com.czh.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by czh on 17-6-9.
 */
@Mapper
public interface UserMapper {
    @Select("select * from tb_user where account = #{account}")
    User getUser(@Param("account") String account);

    @Insert("insert into tb_user(id, account, password, email, phone, gender, nickname, realname) value(#{id}, #{account}, #{password}, #{email}, #{phone}, #{gender}, #{nickname}, #{realname})")
    String addUser(@Param("user") User user);
}
