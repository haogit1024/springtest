package com.czh.controller;

import com.czh.dao.UserDao;
import com.czh.entity.User;
import com.czh.model.LoginModel;
import com.czh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by czh on 17-6-9.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final static Logger log = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String account, @RequestParam String password, @RequestParam int keepLogin, HttpSession session) throws IOException {
        ModelAndView model = new ModelAndView();
        if (account == null || "".equals(account) || password == null || "".equals(password)) {
            model.addObject("status", 1);
            model.addObject("statusCode","");
            return model;
        }
        User user = userService.getUser(account);
        if (user == null) {
            model.addObject("status", 1);
            model.addObject("statusCode","该用户不存在");
            return model;
        }
        if (password.equals(user.getPassword())) {
            model.addObject("status", 0);
            session.setAttribute("uid", user.getId());
            return model;
        } else {
            model.addObject("status",1);
            model.addObject("statusCode","密码错误");
            return model;
        }
    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public ModelAndView insertUser(@RequestParam String account, @RequestParam String password){
        ModelAndView model = new ModelAndView();
        if (account == null || "".equals(account) || password == null || "".equals(password)) {
            model.addObject("status", 1);
            model.addObject("statusCode","");
            return model;
        }
        User user = userService.getUser(account);
        if (user != null) {
            model.addObject("status",1);
            model.addObject("statusCode", "该用户名已存在");
            return model;
        }
        User newUser = new User();
        newUser.setAccount(account);
        newUser.setPassword(password);
        String id = userService.insertUser(newUser);
        if (id != null) {
            model.addObject("status", 0);
            return model;
        } else {
            model.addObject("status", 1);
            model.addObject("statusCode", "数据库操作失败");
            return model;
        }
    }

}
