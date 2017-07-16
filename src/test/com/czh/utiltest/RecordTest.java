package com.czh.utiltest;

import com.czh.dao.RecordDao;
import com.czh.entity.Record;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by czh on 17-7-15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordTest {

    @Autowired
    private RecordDao recordDao;

    @Test
    public void testGet() {
        Record record = recordDao.getRecord(1);
        System.out.println(record);
    }

    @Test
    public void testGetByTid(){
        List<Record> list = recordDao.getRecordByTid(1);
        for (Record record : list) {
            System.out.println(record);
        }
    }

    @Test
    public void testInsert(){
        Record record = new Record();
        record.setCreateTime((int)new Date().getTime());
        record.setTime((int)new Date().getTime());
        record.setGrade(100);
        record.setMoney(10.5f);
        record.setTid(1);
        int i = recordDao.insertRecord(record);
        System.out.println(i);
    }

    @Test
    public void testUpdate(){
        Record record = recordDao.getRecord(1);
        record.setMoney(100f);
        boolean flag = recordDao.updateRecord(record);
        if (flag) {
            System.out.println(recordDao.getRecord(1));
        }
    }
}
