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
    @Autowired
    private JdbcTemplate jdbcTemplate;

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


//    public User getMyUserby(String nickname) {
//        return this.sqlSession.selectOne("getUserByNickname", nickname);
//    }

//    public boolean insertUser(User user) {
//        String sql = "insert into tb_user(id, account, password, email, phone, gender, nickname, realname) values(?,?,?,?,?,?,?,?)";
//        int i = jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement ps = connection.prepareStatement(sql);
//                ps.setObject(1, user.getId());
//                ps.setObject(2, user.getAccount());
//                ps.setObject(3, user.getPassword());
//                ps.setObject(4, user.getEmail());
//                ps.setObject(5, user.getPhone());
//                ps.setObject(6, user.getGender());
//                ps.setObject(7, user.getNickname());
//                ps.setObject(8, user.getRealname());
//                return ps;
//            }
//        });
//        if (i > 0) {
//            return true;
//        }
//        return false;
//    }

    //    public User getUser(String account) {
//        User user = jdbcTemplate.queryForObject("select * from tb_user where accoutn = ?", new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet resultSet, int i) throws SQLException {
//                User user = new User();
//                user.setAccount(resultSet.getString("account"));
//                user.setEmail(resultSet.getString("email"));
//                user.setGender(resultSet.getInt("gender"));
//                user.setId(resultSet.getString("id"));
//                user.setNickname(resultSet.getString("nickname"));
//                user.setPassword(resultSet.getString("password"));
//                user.setPhone(resultSet.getString("phone"));
//                user.setRealname(resultSet.getString("realname"));
//                return user;
//            }
//        }, account);
//        return user;
//    }
}
