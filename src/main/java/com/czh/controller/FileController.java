package com.czh.controller;

import com.czh.dto.FileDTO;
import com.czh.entity.Error;
import com.czh.entity.File;
import com.czh.exception.DatabaseException;
import com.czh.exception.NotFoundException;
import com.czh.model.FileModel;
import com.czh.service.FileService;
import com.czh.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/files")
public class FileController {
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private FileService fileService;

    private final Logger log = Logger.getLogger(FileController.class);

    @GetMapping(value = "")
    public List<File> getFileList(@RequestParam(name = "parsonId", required = false) Integer parsonId, @RequestAttribute("uid") int uid){
        if (null == parsonId) {
            parsonId = 0;
        }
        return fileService.getFileByParsonId(uid, parsonId);
    }

    @PostMapping(value = "")
    public File insertFile(@RequestPart("file") MultipartFile file, @RequestPart(value = "parsonId", required = false) Integer parsonId,
                           @RequestPart(value = "md5", required = false) String md5, @RequestAttribute("uid") int uid, HttpSession session) throws IOException {
        String parsonPath;
        if (null == parsonId) {
            parsonId = 0;
            parsonPath = "/";
        } else {
            File parsonFile = fileService.getFileById(parsonId);
            if (null == parsonFile) throw new NotFoundException(parsonId);
            parsonPath = parsonFile.getFilename();
        }
        File fileData = fileUtil.save(file, session, uid);
        fileData.setParsonId(parsonId);
        fileData.setParsonPath(parsonPath);
        fileData.setStatus(1);
        int id = fileService.insertFile(fileData);
        fileData.setId(id);
        return fileData;
    }

    @GetMapping(value = "/{id}")
    public File getFile(@PathVariable int id){
        File file = fileService.getFileById(id);
        if (null == file) throw new NotFoundException(id);
        return file;
    }

    @PutMapping(value = "/{id}")
    public File updateFile(@PathVariable int id, @RequestBody FileModel fileModel){
        File file = fileService.getFileById(id);
        if (null == file) throw new NotFoundException(id);
        file.setFilename(fileModel.getName());
        boolean flag = fileService.updateFile(file);
        if (!flag) throw new DatabaseException("file", "update", file.toString());
        return file;
    }


}
