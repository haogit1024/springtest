package com.czh.controller;

import com.czh.dao.UserDao;
import com.czh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by czh on 17-6-4.
 */
@Controller
public class TestController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView test(HttpSession session){
        ModelAndView model = new ModelAndView();
        User user = userDao.getUser("admin");
        model.addObject(user);
        String path = session.getServletContext().getContextPath();
        model.addObject(path);
        return model;
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public ModelAndView test1(){
        ModelAndView model = new ModelAndView();

        return model;
    }

}
