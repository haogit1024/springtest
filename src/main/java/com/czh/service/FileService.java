package com.czh.service;

import com.czh.dao.FileDao;
import com.czh.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileDao fileDao;

    public FileEntity getFileById(int id){
        return fileDao.getFileById(id);
    }

    public List<FileEntity> listFileByUid(int uid) {
        return fileDao.listFileByUid(uid);
    }

    public List<FileEntity> listFileByParsonId(int uid, int parsonId) {
        return this.fileDao.listFileByParsonId(uid,parsonId);
    }

    public int insertFile(FileEntity file) {
        return fileDao.insertFile(file);
    }

    public boolean updateFile(FileEntity file) {
        return fileDao.updateFile(file);
    }
}
