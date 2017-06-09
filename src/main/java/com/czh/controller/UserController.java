package com.czh.controller;

import com.czh.dao.UserDao;
import com.czh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * Created by czh on 17-6-9.
 */
@Controller
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user/insert")
    public ModelAndView insertUser(){
        ModelAndView model = new ModelAndView();
        User user = new User();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setId(uuid);
        user.setRealname("czh");
        user.setPhone("12345678901");
        user.setPassword("root");
        user.setNickname("hao");
        user.setGender(1);
        user.setEmail("a.com");
        user.setAccount("admin");
        userDao.insertUser(user);
        return model;
    }
}
