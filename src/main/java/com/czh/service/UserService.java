package com.czh.service;

import com.czh.dao.UserDao;
import com.czh.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by czh on 17-7-2.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserEntity getUser(String account){
        return userDao.getUser(account);
    }

    public int insertUser(UserEntity user) {
        return userDao.insertUser(user);
    }

    public boolean updateUser(UserEntity user) {
        return userDao.updateUser(user);
    }
}
