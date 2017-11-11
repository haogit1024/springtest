package com.czh.controller;

import com.czh.entity.File;
import com.czh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping(value = "")
    public List<File> getFileList(HttpServletRequest request, @RequestParam Integer parsonId){
        int uid = Integer.valueOf((String) request.getAttribute("uid"));
//        return fileService.getFileByUid(Integer.valueOf((String) uid));
        return fileService.getFileByParsonId(uid, parsonId);
    }

//    @GetMapping(value = "/{parsonId}")
//    public List<File> getFileListByParsonId(@PathVariable int parsonId, HttpServletRequest request){
//        int uid = Integer.valueOf((String) request.getAttribute("uid"));
//        return fileService.getFileByParsonId(uid, parsonId);
//    }

//    public File postFile(){
//
//    }
}
