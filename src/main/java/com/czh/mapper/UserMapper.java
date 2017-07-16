package com.czh.mapper;

import com.czh.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by czh on 17-6-9.
 */
@Mapper
public interface UserMapper {
    @Select("select * from tb_user where account = #{account}")
    User getUser(@Param("account") String account);

    @Insert("insert into tb_user(id, account, password, email, phone, gender, nickname, realname) value(#{id}, #{account}, #{password}, #{email}, #{phone}, #{gender}, #{nickname}, #{realname})")
    String addUser(User user);

    @Update("update tb_user set account = #{account}, password = #{password}, email = #{email}, phone = #{phone}, gender = #{gender}, nickname = #{nickname}, realname = #{realname}, status = #{status} where id = #{id}")
    int updateUser();
}
