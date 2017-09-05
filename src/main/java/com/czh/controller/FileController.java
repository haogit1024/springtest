package com.czh.controller;

import com.czh.entity.FileRouting;
import com.czh.response.StatusCode;
import com.czh.service.FileService;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


/**
 * Created by czh on 17-7-1.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    private static Logger log = Logger.getLogger(FileController.class);
    private static final int BUFFER_SIZE = 100 * 1024;

    @Autowired
    private FileService fileService;

    //根据uid获取所有文件
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView getFileByUid(@RequestParam String uid, HttpSession session){
        ModelAndView model = new ModelAndView();
        if (uid == null || "".equals(uid)){
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
        }
        List<FileRouting> files = fileService.getFileByUid(uid);
        model.addObject("status",0);
        model.addObject("files", files);
        return model;
    }

    //TODO 返回一个下载文件http响应,根据http://www.cnblogs.com/dengyg200891/p/6012802.html返回mime类型
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView downLoad(@RequestParam String url, HttpResponse response) {
        ModelAndView model = new ModelAndView();
        if (null == url || url.equals("")) {
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
        }

        return model;
    }

    //TODO 根据md5优化保存过程
    //保存文件
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView uploadFile(@RequestParam MultipartFile file,@RequestParam String name, HttpSession session){
        ModelAndView model = new ModelAndView();
        try {
            //生成uuid名字
            String fileName = dealWithFileName(file.getOriginalFilename());
            String realPath = getRealPath(session);
            String relativePath = "file";
            File folder = new File(realPath + relativePath +"/" + name +"/");
            if (!folder.exists()) {
                log.info("创建文件夹");
                folder.mkdir();
            }
            File destFile = new File(folder, fileName);
            FileOutputStream fs = new FileOutputStream(destFile);
            InputStream in = file.getInputStream();
            copy(in, fs);
            FileRouting fileRouting = new FileRouting();
            fileRouting.setUid(name);
            fileRouting.setOriginalFilename(file.getOriginalFilename());
            fileRouting.setUrl(getDomain()+relativePath+"/"+name+"/"+fileName);
            fileRouting.setMd5(getMD5(in));
            int id = fileService.insertFile(fileRouting);
            fileRouting.setId(id);

            model.addObject("file", fileRouting);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("status", 1);
            model.addObject("statusCode","保存文件失败");
        }
        return model;
    }

    private String dealWithFileName(String fileName){
        String uuid = UUID.randomUUID().toString();
        StringBuilder newFileName = new StringBuilder();
        newFileName.append(uuid);
        if (fileName.contains(".")) {
            String ext = fileName.substring(fileName.lastIndexOf("."));
            if (!"".equals(ext)) {
                newFileName.append(ext);
            }
        }
        return newFileName.toString();
    }

    private String getDomain() {
        try {
            Properties prop = new Properties();
            String propFileName = "/file.properties";
            InputStream in = FileController.class.getResourceAsStream(propFileName);
            prop.load(in);
            String domain = prop.getProperty("domain");
            return domain;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getRealPath(HttpSession session) {
        return session.getServletContext().getRealPath("/");
    }

    //TODO 把获取md5编码整合到copy方法中
    private String getMD5(InputStream inputStream) {
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream, BUFFER_SIZE);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = bis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            return bigInt.toString(16);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void copy(InputStream in, FileOutputStream fs) {
        try {
            in = new BufferedInputStream(in, BUFFER_SIZE);
            BufferedOutputStream bos = new BufferedOutputStream(fs, BUFFER_SIZE);
            int len;
            byte[] bytes = new byte[BUFFER_SIZE];
            while ((len = in.read(bytes)) > 0) {
                bos.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
