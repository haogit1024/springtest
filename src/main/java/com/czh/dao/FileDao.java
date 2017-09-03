package com.czh.dao;

import com.czh.entity.FileRouting;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileDao {
    private SqlSession session;

    public FileDao(SqlSession session){
        this.session = session;
    }

    public FileRouting getFileById(int id) {
        return this.session.selectOne("getFileById", id);
    }

    public List<FileRouting> getFileByUid(String uid) {
        return this.session.selectList("getFileByUid", uid);
    }

    public int insertFile(FileRouting fileRouting) {
        int i = this.session.insert("insertFile", fileRouting);
        if (i > 0) {
            return fileRouting.getId();
        }
        return -1;
    }
}
