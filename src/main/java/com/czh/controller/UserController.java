package com.czh.controller;

import com.czh.entity.UserEntity;
import com.czh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by czh on 17-6-9.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final static Logger log = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView login(@RequestBody LoginModel loginModel, HttpSession session) throws IOException {
//        String account = loginModel.getAccount();
//        String password = loginModel.getPassword();
//        ModelAndView model = new ModelAndView();
//        if (account == null || "".equals(account) || password == null || "".equals(password)) {
//            model.addObject("status", 1);
//            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
//            return model;
//        }
//        User user = userService.getUser(account);
//        if (user == null) {
//            model.addObject("status", 1);
//            model.addObject("statusCode","该用户不存在");
//            return model;
//        }
//        if (password.equals(user.getPassword())) {
//            model.addObject("status", 0);
//            user.setPassword("");
//            model.addObject("user", user);
//            session.setAttribute("uid", user.getId());
//            return model;
//        } else {
//            model.addObject("status",1);
//            model.addObject("statusCode","密码错误");
//            return model;
//        }
//    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public ModelAndView insertUser(@RequestParam String account, @RequestParam String password){
        ModelAndView model = new ModelAndView();
        if (account == null || "".equals(account) || password == null || "".equals(password)) {
            model.addObject("status", 1);
            model.addObject("statusCode","");
            return model;
        }
        UserEntity user = userService.getUser(account);
        if (user != null) {
            model.addObject("status",1);
            model.addObject("statusCode", "该用户名已存在");
            return model;
        }
        UserEntity newUser = new UserEntity();
        newUser.setAccount(account);
        newUser.setPassword(password);
        int id = userService.insertUser(newUser);
        if (id > 0) {
            model.addObject("status", 0);
            return model;
        } else {
            model.addObject("status", 1);
            model.addObject("statusCode", "数据库操作失败");
            return model;
        }
    }

}
