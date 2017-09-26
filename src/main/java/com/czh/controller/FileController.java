package com.czh.controller;

import com.czh.entity.FileRouting;
import com.czh.response.StatusCode;
import com.czh.service.FileService;
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
import java.util.Date;
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
    private static final int BUFFER_SIZE = 64;

    @Autowired
    private FileService fileService;

    //根据uid获取文件
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView getFileByUid(@RequestParam String uid, @RequestParam String parsonPath,HttpSession session){
        ModelAndView model = new ModelAndView();
        if (uid == null || "".equals(uid)){
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
        }
        if (parsonPath == null || parsonPath.equals("")) {
            parsonPath = "/";
        }
        List<FileRouting> files = fileService.getFileByParsonPath(uid, parsonPath);
        model.addObject("status",0);
        model.addObject("files", files);
        return model;
    }

    //TODO 返回一个下载文件http响应,根据http://www.cnblogs.com/dengyg200891/p/6012802.html返回mime类型
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView downLoad(@RequestParam String url) {
        ModelAndView model = new ModelAndView();
        if (null == url || url.equals("")) {
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
        }

        return model;
    }

    //新建文件夹
    @RequestMapping(value = "/newFolder", method = RequestMethod.GET)
    public ModelAndView newFolder(@RequestParam String parsonPath, @RequestParam String name, @RequestParam String uid){
        ModelAndView model = new ModelAndView();
        FileRouting file = new FileRouting();
        file.setType("folder");
        file.setParsonPath(parsonPath);
        file.setOriginalFilename(name);
        file.setUid(uid);
        file.setStatus(1);
        int id = fileService.insertFile(file);
        if (id > 1) {
            model.addObject("status",0);
            model.addObject("file", file);
        } else {
            model.addObject("status",1);
            model.addObject("statusCode",StatusCode.FAILOPERATIONDATABASE.toString());
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
            //生成uuid和后缀名
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            StringBuilder newFileName = new StringBuilder();
            //TODO 暂时改成时间戳
            newFileName.append(new Date().getTime());
            String ext = "";
            if (fileName.contains(".")) {
                ext = fileName.substring(fileName.lastIndexOf("."));
                if (!"".equals(ext)) {
                    newFileName.append(ext);
                }
            }

            String dealFileName = newFileName.toString();
            log.info("save file name is = " + dealFileName);
            String realPath = getRealPath(session);
            String relativePath = "file";
            File folder = new File(realPath + relativePath +"/" + name +"/");
            if (!folder.exists()) {
                log.info("创建文件夹");
                folder.mkdir();
            }
            File destFile = new File(folder, dealFileName);
            FileOutputStream fs = new FileOutputStream(destFile);
            InputStream in = file.getInputStream();
            copy(in, fs, file.getSize());
            log.info("file originalFilename = " + file.getOriginalFilename());
            FileRouting fileRouting = new FileRouting();
            fileRouting.setUid(name);
            fileRouting.setOriginalFilename(file.getOriginalFilename());
            fileRouting.setUrl(getDomain()+relativePath+"/"+name+"/"+dealFileName);
//            fileRouting.setMd5(getMD5(in));
            fileRouting.setStatus(1);
            fileRouting.setType(getFileType(ext));
            int id = fileService.insertFile(fileRouting);
            fileRouting.setId(id);

            model.addObject("status", 0);
            model.addObject("file", fileRouting);
        } catch (Exception e) {
            e.printStackTrace();
            model.addObject("status", 1);
            model.addObject("statusCode","保存文件失败");
        }
        return model;
    }

    //根据文件后缀返回font awesome图标类型
    //TODO 这里不全文件格式判断
    private String getFileType(String ext) {
        if (ext.equals(".text")) {
            return "text";
        } else if (ext.equals(".java") || ext.equals(".html") || ext.equals(".js") || ext.equals("php")) {
            return "code";
        } else if (ext.equals(".mp4") || ext.equals(".avi") || ext.equals(".rmvb") || ext.equals(".wmv") || ext.equals(".mov") || ext.equals(".flv")) {
            return "movie";
        } else if (ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".png") || ext.equals(".gif")) {
            return "image";
        } else if (ext.equals(".mp3")) {
            return "audio";
        } else if (ext.equals(".xls") || ext.equals(".xlsx") || ext.equals("csv")) {
            return "excel";
        } else if (ext.equals(".docx") || ext.equals(".doc")) {
            return "word";
        } else if (ext.equals(".pdf")) {
            return "pdf";
        } else if (ext.equals(".rar") || ext.equals(".zip") || ext.equals(".7z")) {
            return "zip";
        } else if (ext.equals(".ppt")) {
            return "powerpoint";
        }
//        return ext.substring(1,ext.length());
        return "";
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
        try {BufferedInputStream bis = new BufferedInputStream(inputStream);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[64];
            int length;
            while ((length = bis.read(buffer,0, 64)) != -1) {
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

    private void copy(InputStream in, FileOutputStream fs, long fileSize) {
        try {
//            in = new BufferedInputStream(in, BUFFER_SIZE);
            BufferedOutputStream bos = new BufferedOutputStream(fs, BUFFER_SIZE);
            int len;
            byte[] bytes;
            //防止bytes大于文件大小
            if (fileSize >= BUFFER_SIZE) {
                bytes = new byte[BUFFER_SIZE];
            } else {
                bytes = new byte[64];
            }
            while ((len = in.read(bytes)) > 0) {
                bos.write(bytes, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
