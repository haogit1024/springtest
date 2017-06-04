package com.czh.controller;

import com.czh.dao.TestDao;
import com.czh.entity.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * Created by czh on 17-6-4.
 */
@Controller
public class TestController {

    @Autowired
    private TestDao testDao;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test(){
        ModelAndView model = new ModelAndView();
        Access access = testDao.getAccess(1);
        model.addObject("test", access);
        return model;
    }
}
