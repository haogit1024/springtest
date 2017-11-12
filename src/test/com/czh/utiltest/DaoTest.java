package com.czh.utiltest;

import com.czh.dao.FileDao;
import com.czh.entity.File;
import com.czh.entity.FileRouting;
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
        File file = fileDao.getFileById(8);
//        System.out.println(file);
        file.setFilename("daotestupdate");
        fileDao.updateFile(file);
    }

}
