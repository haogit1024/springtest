package com.czh.dao;

import com.czh.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by czh on 17-6-9.
 */
@Repository
public class UserDao {

    private final SqlSession sqlSession;

    public UserDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public User getUser(String account) {
        return this.sqlSession.selectOne("getUser", account);
    }

    public String insertUser(User user) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        user.setId(uuid);
        int i = this.sqlSession.insert("addUser", user);
        if (i > 0) {
            return user.getId();
        }
        return null;
    }

    public boolean updateUser(User user){
        int i = this.sqlSession.update("updateUser", user);
        System.out.println("i = " + i);
        if (i > 0) {
            return true;
        }
        return false;
    }

}
