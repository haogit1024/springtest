package com.czh.controller;

import com.czh.dao.UserDao;
import com.czh.entity.User;
import com.czh.model.LoginModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by czh on 17-6-9.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final static Logger log = Logger.getLogger(UserController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestBody LoginModel loginModel, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView model = new ModelAndView();
        String account = loginModel.getAccount();
        String password = loginModel.getPassword();
        boolean keepLogin = loginModel.isKeepLogin();
        log.info("account = " + account);
        log.info("password = " + password);
        return model;

    }

}
