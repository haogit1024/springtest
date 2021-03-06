package com.czh.controller;


import com.czh.entity.FileEntity;
import com.czh.exception.DatabaseException;
import com.czh.exception.NotFoundException;
import com.czh.model.FileModel;
import com.czh.service.FileService;
import com.czh.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/files")
public class FileController {
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private FileService fileService;

    private final Logger log = Logger.getLogger(FileController.class);

    /**
     * 获取uid的所有parsonId为某个值的文件
     * @param parsonId
     * @param uid
     * @return
     */
    @GetMapping(value = "")
    public List<FileEntity> getFileList(@RequestParam(name = "parsonId", required = false) Integer parsonId, @RequestAttribute("uid") int uid){
        if (null == parsonId) {
            parsonId = 0;
        }
        log.info("parsonId = " + parsonId);
        return fileService.listFileByParsonId(uid, parsonId);
    }

    /**
     * 新建文件
     * 保存用户上传的文件
     * @param file 文件
     * @param fileModel 文件前端模型
     * @param uid 用户id
     * @param session session,用于获取实际物理路径
     * @return 导入文件详情
     * @throws IOException 可能发生的io异常 TODO 用try_catch处理
     */
    @PostMapping(value = "")
    public FileEntity insertFile(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart("fileModel") FileModel fileModel,
                                 @RequestAttribute("uid") int uid, HttpSession session) throws IOException {
        String parsonPath;
        Integer parsonId = fileModel.getParentId();
        if (null == parsonId || parsonId == 0) {
            parsonId = 0;
            parsonPath = "/";
        } else {
            FileEntity parsonFile = fileService.getFileById(parsonId);
            if (null == parsonFile) throw new NotFoundException(parsonId);
            parsonPath = parsonFile.getFilename();
        }
        if (null != file) {
            FileEntity fileData = fileUtil.save(file, session, uid);
            fileData.setParsonId(parsonId);
            fileData.setParsonPath(parsonPath);
            fileData.setStatus(1);
            fileData.setTime(new Date().getTime());
            int id = fileService.insertFile(fileData);
            fileData.setId(id);
            return fileData;
        } else {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setTime(new Date().getTime());
            fileEntity.setType("folder");
            fileEntity.setFilename(fileModel.getName());
            fileEntity.setStatus(1);
            fileEntity.setParsonId(parsonId);
            fileEntity.setUid(uid);
            fileEntity.setParsonPath(parsonPath);
            fileService.insertFile(fileEntity);
            return fileEntity;
        }

    }

    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public FileEntity getFile(@PathVariable int id){
        FileEntity file = fileService.getFileById(id);
        if (null == file) throw new NotFoundException(id);
        return file;
    }

    /**
     * 根据id更新文件
     * @param id
     * @param fileModel
     * @return
     */
    @PutMapping(value = "/{id}")
    public FileEntity updateFile(@PathVariable int id, @RequestBody FileModel fileModel){
        FileEntity file = fileService.getFileById(id);
        if (null == file) throw new NotFoundException(id);
        file.setFilename(fileModel.getName());
        boolean flag = fileService.updateFile(file);
        if (!flag) throw new DatabaseException("file", "update", file.toString());
        return file;
    }

    /**
     * 根据id删除文件
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public FileEntity deleteFile(@PathVariable int id) {
        FileEntity file = fileService.getFileById(id);
        if (null == file) throw new NotFoundException(id);
        file.setStatus(2);
        boolean flag = fileService.updateFile(file);
        if (!flag) throw new DatabaseException("file", "update", file.toString());
        return file;
    }

    @GetMapping(value = "download/{id}")
    public ResponseEntity download(@PathVariable int id, @RequestAttribute("uid") int uid,HttpSession session){
        log.info("下载文件");
        FileEntity file = fileService.getFileById(id);
        if (null == file) throw new NotFoundException(id);
        String filename = file.getFilename();
        String realname = file.getRealname();
        String filepath = fileUtil.getFilePath(uid, realname, session);
        try {
            log.info("filepath = " + filepath);
            FileInputStream fis = new FileInputStream(filepath);
            HttpHeaders headers = new HttpHeaders();
            headers.add("content-disposition", "attachment;filename=" + filename);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
            return responseEntity;
        }  catch (IOException e) {
            e.printStackTrace();
            //todo throw custom exception
        }
        return null;
    }

}
