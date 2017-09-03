package com.czh.utiltest;

import com.czh.dao.FileDao;
import com.czh.dao.UserDao;
import com.czh.entity.FileRouting;
import com.czh.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by czh on 17-6-9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DaoTest {
    @Autowired
    private FileDao fileDao;

    @Test
    public void testDao(){
        FileRouting fileRouting = new FileRouting();
        fileRouting.setUid("aaa");
        fileRouting.setMd5("bbbb");
        fileRouting.setOriginalFilename("cccc");
        int id = fileDao.insertFile(fileRouting);
        System.out.println(id);
        FileRouting file = fileDao.getFileById(id);
        System.out.println(file);
//        List<FileRouting> list = fileDao.getFileByUid("dddd");
//        System.out.println("size = " + list.size());
//        for (FileRouting f : list) {
//            System.out.println(f);
//        }
    }

}
