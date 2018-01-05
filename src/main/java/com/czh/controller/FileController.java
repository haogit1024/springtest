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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
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
    public List<File> getFileList(@RequestParam(name = "parsonId", required = false) Integer parsonId, @RequestAttribute("uid") int uid){
        if (null == parsonId) {
            parsonId = 0;
        }
        log.info("parsonId = " + parsonId);
        return fileService.getFileByParsonId(uid, parsonId);
    }

    /**
     * 新建文件
     * 保存用户上传的文件
     * @param file 文件
     * @param parsonId 父id
     * @param md5 md5码
     * @param uid 用户id
     * @param session session,用于获取实际物理路径
     * @return
     * @throws IOException
     */
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

    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public File getFile(@PathVariable int id){
        File file = fileService.getFileById(id);
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
    public File updateFile(@PathVariable int id, @RequestBody FileModel fileModel){
        File file = fileService.getFileById(id);
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
    public File deleteFile(@PathVariable int id) {
        File file = fileService.getFileById(id);
        if (null == file) throw new NotFoundException(id);
        file.setStatus(2);
        boolean flag = fileService.updateFile(file);
        if (!flag) throw new DatabaseException("file", "update", file.toString());
        return file;
    }

    @GetMapping(value = "download/{id}")
    public ResponseEntity download(@PathVariable int id, @RequestAttribute("uid") int uid,HttpSession session){
        log.info("下载文件");
        File file = fileService.getFileById(id);
        if (null == file) throw new NotFoundException(id);
        String filename = file.getFilename();
        String filepath = fileUtil.getFilePath(uid, filename, session);
        try {
            FileInputStream fis = new FileInputStream(filepath);
            HttpHeaders headers = new HttpHeaders();
            headers.add("content-disposition", "attachment;filename=" + filename);
            byte[] bytes = new byte[fis.available()];
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
            return responseEntity;
        }  catch (IOException e) {
            e.printStackTrace();
            //todo throw custom exception
        }
        return null;

    }

    /**
     * 下载文件
     */
    @GetMapping(value = "download1/{id}")
    public void download(@PathVariable int id, HttpServletResponse response) throws IOException {
//        File file = fileService.getFileById(id);
//        if (null == file) throw new NotFoundException(id);
        String dept = "";
        String fileName = "";
        response.setContentType("application/octet-stream;charset=UTF-8");
//        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(dept + fileName +".xls", "UTF-8"));
        response.setHeader("content-disposition", "attachment;filename=test.PNG");
        OutputStream os = response.getOutputStream();
        java.io.File file = new java.io.File("C:\\Users\\czh\\Desktop\\collector_interface.png");
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[64];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer);
        }
        fis.close();
        os.close();
    }

    @GetMapping(value = "download2/{id}")
    public ResponseEntity download1(@PathVariable int id) throws IOException {
        java.io.File file = new java.io.File("C:\\Users\\czh\\Desktop\\sms.sql");
        InputStream in = new FileInputStream(file);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment;filename=sms.sql");
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        return responseEntity;
    }
}
