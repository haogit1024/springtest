package com.czh.mapper;

import com.czh.entity.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by czh on 17-7-15.
 */
@Mapper
public interface BookMapper {

    @Select("select * from tb_book where id = #{id}")
    Book getBookById(@RequestParam int id);

    @Select("select * from tb_book where uid = #{uid}")
    List<Book> getBookByUid(@RequestParam String uid);

    @Insert("insert into tb_book(name, uid, budget) values(#{name}, #{uid}, #{budget})")
    @SelectKey(statement = "select LAST_INSERT_ID()", before = false, keyProperty = "id", resultType = int.class)
    int insertBook(Book book);

    @Update("update tb_book set name = #{name}, uid = #{uid}, budget = #{budget} where id = #{id}")
    int updateBook(Book book);
}
