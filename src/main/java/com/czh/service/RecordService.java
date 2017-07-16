package com.czh.service;

import com.czh.dao.RecordDao;
import com.czh.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by czh on 17-7-15.
 */
@Service
public class RecordService {

    @Autowired
    private RecordDao recordDao;

    public Record getRecord(int id) {
        return recordDao.getRecord(id);
    }

    public List<Record> getRecordByTid(int tid) {
        return recordDao.getRecordByTid(tid);
    }

    public int insertRecord(Record record) {
        return recordDao.insertRecord(record);
    }

    public boolean updateRecord(Record record) {
        return recordDao.updateRecord(record);
    }
}
