package com.czh.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Properties;
import java.util.UUID;


/**
 * Created by czh on 17-7-1.
 */
@Controller
public class FileController {

    private static Logger log = Logger.getLogger(FileController.class);
    private static final int BUFFER_SIZE = 100 * 1024;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam MultipartFile file,@RequestParam String name, HttpSession session){
        ModelAndView model = new ModelAndView();
        try {
            String fileName = dealWithFileName(file.getOriginalFilename());
            String realPath = getRealPath(session);
            String relativePath = "file";
            File folder = new File(realPath + relativePath);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File destFile = new File(folder, fileName);
            FileOutputStream fs = new FileOutputStream(destFile);
            InputStream in = file.getInputStream();
            copy(in, fs);
        } catch (Exception e) {

        }
        return model;
    }

    private String dealWithFileName(String fileName){
        String uuid = UUID.randomUUID().toString();
        StringBuilder newFileName = new StringBuilder();
        newFileName.append(uuid);
        if (fileName.contains(".")) {
            String ext = fileName.substring(fileName.lastIndexOf("."));
            if (null != ext && "".equals(ext)) {
                newFileName.append(ext);
            }
        }
        return newFileName.toString();
    }

    private String getDomain() {
        try {
            Properties prop = new Properties();
            String propFileName = "file.properties";
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
