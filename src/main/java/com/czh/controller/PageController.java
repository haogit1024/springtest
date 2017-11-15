package com.czh.controller;

import com.czh.entity.File;
import com.czh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/page")
public class PageController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/files/{uid}", method = RequestMethod.GET)
    public String files(@PathVariable int uid){
//        List<File> files = fileService.getFileByUid(uid);
//        model.addAttribute("files", files);
//        File file = fileService.getFileById(uid);
//        map.put("file", file);
//        model.addAttribute("file", file);
        return "test";
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }

}
