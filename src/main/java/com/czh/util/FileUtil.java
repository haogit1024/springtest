package com.czh.util;

import com.czh.App;
import com.czh.entity.FileEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 处理文件的工具类
 */
@Component
public class FileUtil {
    private int BUFFER_SIZE = 1024 * 100;
    private final Logger log = Logger.getLogger(FileUtil.class);
    //TODO 这里不全文件格式判断
    public String getFileType(String ext) {
        if (ext.equals(".text")) {
            return "text";
        } else if (ext.equals(".java") || ext.equals(".html") || ext.equals(".js") || ext.equals(".php") || ext.equals(".xml") || ext.equals(".c")) {
            return "code";
        } else if (ext.equals(".mp4") || ext.equals(".avi") || ext.equals(".rmvb") || ext.equals(".wmv") || ext.equals(".mov") || ext.equals(".flv")) {
            return "movie";
        } else if (ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".png") || ext.equals(".gif") || ext.equals(".PNG")) {
            return "image";
        } else if (ext.equals(".mp3")) {
            return "audio";
        } else if (ext.equals(".xls") || ext.equals(".xlsx") || ext.equals("csv")) {
            return "excel";
        } else if (ext.equals(".docx") || ext.equals(".doc")) {
            return "word";
        } else if (ext.equals(".pdf")) {
            return "pdf";
        } else if (ext.equals(".rar") || ext.equals(".zip") || ext.equals(".7z") || ext.equals("tar.gz")) {
            return "zip";
        } else if (ext.equals(".ppt")) {
            return "powerpoint";
        }
//        return ext.substring(1,ext.length());
        return "";
    }

    /**
     * 根据文件输入流返回输入码
     * @param inputStream 文件的输入流
     * @return md5码
     */
    public String getMD5(InputStream inputStream) {
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

    public FileEntity save(MultipartFile file, HttpSession session, int uid) throws IOException {
        //获取文件的名字,大小
        String fileName = file.getOriginalFilename();
        log.info("保存文件:文件名=" + fileName);
        long size = file.getSize();
        //服务器真实路径
        String realPath = session.getServletContext().getRealPath("/");
        //相对路径,项目中的文件夹
        String relativePath = "files";
        //保存文件的文件夹路径   物理地址 + 相对路径 + uid
        String folderPath = realPath + relativePath + "/" + uid + "/";
        //获取文件夹对象
        File folder = new File(folderPath);
        //如果不存在则创建文件夹
        if (!folder.exists()) {
            folder.mkdir();
        }
        //目标文件路径
        StringBuilder newFileName = new StringBuilder();
        //创建uuid保存到真实的文件中
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        newFileName.append(uuid);
        //文件后缀
        String ext = "";
        if (fileName.contains(".")) {
            ext = fileName.substring(fileName.lastIndexOf("."));
            if (!"".equals(ext)) {
                newFileName.append(ext);
            }
        }
        //创建目标文件
        File realFile = new File(folder, newFileName.toString());

        InputStream in = file.getInputStream();
        FileOutputStream fos = new FileOutputStream(realFile);
        //保存
        String md5 = copy(in, fos);
        //生产可访问url  域名 + 项目名 + 相对地址 + uid + 保存的文件名
        String url = App.DOMAIN + "/" + App.PROJECT_NAME + "/" + relativePath + "/" + uid + "/" + newFileName.toString();
        //将相关信息保存在fileData中返回
        FileEntity fileData = new FileEntity();
        fileData.setFilename(fileName);
        fileData.setRealname(newFileName.toString());
        fileData.setSize(size);
        fileData.setMd5(md5);
        fileData.setType(getFileType(ext));
        fileData.setUrl(url);
        fileData.setUid(uid);
        return fileData;
    }

    public String copy(InputStream in, FileOutputStream fs) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(fs, BUFFER_SIZE);
            MessageDigest md = MessageDigest.getInstance("MD5");
            int len;
            byte[] bytes = new byte[BUFFER_SIZE];
            while ((len = in.read(bytes)) > 0) {
                bos.write(bytes, 0, len);
                md.update(bytes, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            bos.flush();
            bos.close();
            fs.close();
            in.close();
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFilePath(int uid, String filename, HttpSession session) {
        //服务器真实路径
        String realPath = session.getServletContext().getRealPath("/");
        String filePath = realPath + "files/" + uid + "/" + filename;
        return filePath;
    }
}
