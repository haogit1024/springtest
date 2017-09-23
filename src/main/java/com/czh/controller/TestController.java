package com.czh.controller;

import com.czh.dao.UserDao;
import com.czh.entity.FileRouting;
import com.czh.entity.User;
import com.czh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;


/**
 * Created by czh on 17-6-4.
 */
@Controller
public class TestController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView test(HttpSession session){
        ModelAndView model = new ModelAndView();
        User user = userDao.getUser("admin");
        model.addObject(user);
        String path = session.getServletContext().getContextPath();
        model.addObject(path);
        return model;
    }

    @RequestMapping(value = "/ftl", method = RequestMethod.GET)
    public String test1() {
        return "test";
    }

    @RequestMapping(value = "testDb")
    public ModelAndView testDataBase(){
        ModelAndView model = new ModelAndView();
        List<FileRouting> fileList = fileService.getFileByUid("test");
        model.addObject("files", fileList);
        return model;
    }
}
