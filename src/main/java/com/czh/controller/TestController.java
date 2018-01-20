package com.czh.controller;

import com.czh.dao.UserDao;
import com.czh.entity.FileRouting;
import com.czh.entity.User;
import com.czh.service.FileService;
import com.czh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by czh on 17-6-4.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private UserService userService;

    private final Logger log = Logger.getLogger(TestController.class);
    @PostMapping(value = "/files")
    public User test(@RequestPart("file") MultipartFile file, @RequestPart(value = "parentId", required = false) String parentId) {
        User user = userService.getUser("admin");
        log.info("fileName = " + file.getOriginalFilename());
        log.info("parentId = " + parentId);
        return user;
    }
}
