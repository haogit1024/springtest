package com.czh.dao;

import com.czh.entity.UserEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * Created by czh on 17-6-9.
 */
@Repository
public class UserDao {

    private final SqlSession sqlSession;

    public UserDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public UserEntity getUser(String account) {
        return this.sqlSession.selectOne("getUser", account);
    }

    public int insertUser(UserEntity user) {

        int i = this.sqlSession.insert("addUser", user);
        if (i > 0) {
            return user.getId();
        }
        return -1;
    }

    public boolean updateUser(UserEntity user){
        int i = this.sqlSession.update("updateUser", user);
        System.out.println("i = " + i);
        if (i > 0) {
            return true;
        }
        return false;
    }

}
