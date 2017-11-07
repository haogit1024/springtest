package com.czh.controller;

import com.czh.entity.User;
import com.czh.exception.LoginException;
import com.czh.jwt.Header;
import com.czh.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login(@RequestBody User user) throws IOException {
        User realUser = userService.getUser(user.getAccount());
        String token = "";
        if (realUser == null) {
            throw new LoginException("用户不存在");
        }
        if (user.getPassword().equals(realUser.getPassword())) {
            Properties prop = new Properties();
            String propFileName = "/db.properties";
            InputStream is = LoginController.class.getResourceAsStream(propFileName);
            prop.load(is);
            String secret = prop.getProperty("secret");

            ObjectMapper mapper = new ObjectMapper();
            Header header = new Header();
            header.setAlg("JWT");
            header.setTyp("HS256");
        } else {
            throw new LoginException("密码错误");
        }
        return token;
    }
}
