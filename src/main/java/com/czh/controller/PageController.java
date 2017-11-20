package com.czh.controller;

import com.czh.entity.File;
import com.czh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/page")
public class PageController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/files/{uid}", method = RequestMethod.GET)
    public String files(Model model, @PathVariable int uid){
        List<File> files = fileService.getFileByParsonId(uid, 0);
        model.addAttribute("files", files);
        return "filemain";
    }

    @RequestMapping(value = "files/list/{parsonId}")
    public String getFileList(Model model, @PathVariable int parsonId, @RequestAttribute int uid) {
        List<File> files = fileService.getFileByParsonId(uid, parsonId);
        model.addAttribute("files", files);
        return "filelist";
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }

}
