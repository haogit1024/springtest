package com.czh.dao;

import com.czh.entity.File;
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

    public File getFileById(int id) {
        return this.session.selectOne("getFileById", id);
    }

    public List<File> listFileByUid(int uid) {
        return this.session.selectList("getFileByUid", uid);
    }

    public List<File> listFileByParsonId(int uid, int parsonId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("uid", uid);
        map.put("parsonId", parsonId);
        return this.session.selectList("getFileByParsonId", map);
    }

    public List<File> listFileByParsonPath(String uid, String parsonPath) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("parsonPath", parsonPath);
        return this.session.selectList("getFileByParsonPath", map);
    }

//    public List<File> getFileByParsonId(String uid, Integer parsonId) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("uid", uid);
//        map.put("parsonId", parsonId);
//        return this.session.selectList("getFileByParsonId", map);
//    }

    public int insertFile(File file) {
        int i = this.session.insert("insertFile", file);
        if (i > 0) {
            return file.getId();
        }
        return -1;
    }

    public boolean updateFile(File file) {
        int i = this.session.update("updateFile", file);
        return i > 0;
    }
}
