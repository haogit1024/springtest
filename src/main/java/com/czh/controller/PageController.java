package com.czh.controller;

import com.czh.entity.File;
import com.czh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping(value = "/page")
public class PageController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/files/{uid}", method = RequestMethod.GET)
    public String files(Model model, @PathVariable int uid){
//        List<File> files = fileService.getFileByUid(uid);
//        model.addAttribute("files", files);
        File file = fileService.getFileById(7);
        model.addAttribute("file", file);
        return "fileList";
    }

}
