package com.czh.controller;

import com.czh.entity.UserEntity;
import com.czh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
    public UserEntity test(@RequestPart("file") MultipartFile file, @RequestPart(value = "parentId", required = false) String parentId) {
        UserEntity user = userService.getUser("admin");
        log.info("fileName = " + file.getOriginalFilename());
        log.info("parentId = " + parentId);
        return user;
    }

    @PostMapping(value = "/batch")
    public UserEntity testBatchFile() {
        UserEntity user = userService.getUser("admin");
        return user;
    }
}
