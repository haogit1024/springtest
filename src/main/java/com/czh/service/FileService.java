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

    public List<File> listFileByUid(int uid) {
        return fileDao.listFileByUid(uid);
    }

    public List<File> listFileByParsonId(int uid, int parsonId) {
        return this.fileDao.listFileByParsonId(uid,parsonId);
    }

    public int insertFile(File file) {
        return fileDao.insertFile(file);
    }

    public boolean updateFile(File file) {
        return fileDao.updateFile(file);
    }
}
