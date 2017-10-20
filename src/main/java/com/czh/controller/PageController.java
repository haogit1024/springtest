package com.czh.controller;

import com.czh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/page")
public class PageController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public String files(Model model, @RequestParam String uid){
//        List<File> files = fileService.getFileByParsonPath(uid)

        return "fileList";
    }

}
