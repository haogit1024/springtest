package com.czh.controller;

import com.czh.entity.File;
import com.czh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping(value = "")
    public List<File> getFileList(){
        List<File> list = fileService.getFileByUid("czh");
        return list;
    }

//    public File postFile(){
//
//    }
}
