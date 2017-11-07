package com.czh.service;

import com.czh.dao.FileDao;
import com.czh.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileDao fileDao;

    public File getFileById(int id){
        return fileDao.getFileById(id);
    }

    public List<File> getFileByUid(String uid) {
        return fileDao.getFileByUid(uid);
    }

    public List<File> getFileByParsonPath(String uid, String parsonPath) {
        return this.fileDao.getFileByParsonPath(uid,parsonPath);
    }

    public int insertFile(File fileRouting) {
        return fileDao.insertFile(fileRouting);
    }


}
