package com.czh.service;

import com.czh.dao.UserDao;
import com.czh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by czh on 17-7-2.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUser(String account){
        return userDao.getUser(account);
    }
}
