package com.czh.controller;

import com.czh.dao.UserDao;
import com.czh.entity.Error;
import com.czh.entity.FileRouting;
import com.czh.entity.User;
import com.czh.exception.NotFoundException;
import com.czh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

@RestController
public class RestTestController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public User test(){
        User user = userDao.getUser("admin");
        if (user == null) throw new NotFoundException(1);
        return user;
    }

    @PutMapping("/put/something")
    public void handle(@RequestBody String body, Writer writer) throws IOException {
        writer.write(body);
    }

    @GetMapping("/get/something")
    @ResponseBody
//    public FileRouting helloWorld() {
//        return fileService.getFileById(4);
//    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error notFound(NotFoundException e){
        return new Error((int)e.getId(), "not found");
    }



}