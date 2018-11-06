package com.czh.utiltest;

import com.czh.dao.CzhTestDao;
import com.czh.dao.FileDao;
import com.czh.database.DynamicDataSourceContextHolder;
import com.czh.entity.CzhTest;
import com.czh.entity.FileEntity;
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
    @Autowired
    private CzhTestDao czhTestDao;

    @Test
    public void testDao(){
        List<FileEntity> files = fileDao.listFileByParsonId(1, 2);
        System.out.println(files.size());
    }

    @Test
    public void testCzh() {
        DynamicDataSourceContextHolder.setDataSources("dev");
        List<CzhTest> list = czhTestDao.listCzhTest();
        list.forEach(System.out::println);
    }

}
