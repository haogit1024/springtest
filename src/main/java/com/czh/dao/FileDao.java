package com.czh.dao;

import com.czh.entity.FileRouting;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<FileRouting> getFileByParsonPath(String uid, String parsonPath) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("parsonPath", parsonPath);
        return this.session.selectList("getFileByParsonPath", map);
    }

    public int insertFile(FileRouting fileRouting) {
        int i = this.session.insert("insertFile", fileRouting);
        if (i > 0) {
            return fileRouting.getId();
        }
        return -1;
    }
}
