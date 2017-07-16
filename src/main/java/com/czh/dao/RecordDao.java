package com.czh.dao;

import com.czh.entity.Record;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by czh on 17-7-15.
 */
@Repository
public class RecordDao {

    private SqlSession session;

    public RecordDao(SqlSession session) {
        this.session = session;
    }

    public Record getRecord(int id) {
        return this.session.selectOne("getRecord",id);
    }

    public List<Record> getRecordByTid(int tid) {
        return this.session.selectList("getRecordByTid", tid);
    }

    public int insertRecord(Record record) {
        int i =  this.session.insert("insertRecord", record);
        if (i > 0) {
            return record.getId();
        }
        return -1;
    }

    public boolean updateRecord(Record record) {
        int i = this.session.update("updateRecord", record);
        return i > 0;
    }
}
