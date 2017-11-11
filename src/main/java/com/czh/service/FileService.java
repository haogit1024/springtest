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

    public List<File> getFileByUid(int uid) {
        return fileDao.getFileByUid(uid);
    }

    public List<File> getFileByParsonId(int uid, int parsonId) {
        return this.fileDao.getFileByParsonId(uid,parsonId);
    }

    public int insertFile(File fileRouting) {
        return fileDao.insertFile(fileRouting);
    }


}
