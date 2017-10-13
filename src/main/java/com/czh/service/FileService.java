package com.czh.service;

import com.czh.dao.FileDao;
import com.czh.entity.FileRouting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileDao fileDao;

    public FileRouting getFileById(int id){
        return fileDao.getFileById(id);
    }

    public List<FileRouting> getFileByUid(String uid) {
        return fileDao.getFileByUid(uid);
    }

    public List<FileRouting> getFileByParsonPath(String uid, String parsonPath) {
        return this.fileDao.getFileByParsonPath(uid,parsonPath);
    }

    public int insertFile(FileRouting fileRouting) {
        return fileDao.insertFile(fileRouting);
    }


}
