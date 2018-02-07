package com.czh.mapper;

import com.czh.entity.UserEntity;
import org.apache.ibatis.annotations.*;

/**
 * Created by czh on 17-6-9.
 */
@Mapper
public interface UserMapper {
    @Select("select * from tb_user where account = #{account}")
    UserEntity getUser(@Param("account") String account);

    @Insert("insert into tb_user(id, account, password, email, phone, gender, nickname, realname) value(#{id}, #{account}, #{password}, #{email}, #{phone}, #{gender}, #{nickname}, #{realname})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
    String addUser(UserEntity user);

    @Update("update tb_user set account = #{account}, password = #{password}, email = #{email}, phone = #{phone}, gender = #{gender}, nickname = #{nickname}, realname = #{realname}, status = #{status} where id = #{id}")
    int updateUser();
}
