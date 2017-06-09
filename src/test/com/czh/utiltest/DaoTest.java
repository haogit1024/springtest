package com.czh.utiltest;

import com.czh.dao.UserDao;
import com.czh.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by czh on 17-6-9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testDao(){
        User user = new User();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setId(uuid);
        user.setRealname("czh");
        user.setPhone("12345678901");
        user.setPassword("root");
        user.setNickname("hao");
        user.setGender(1);
        user.setEmail("a.com");
        user.setAccount("admin");
        boolean b = userDao.insertUser(user);
        System.out.println(b);
    }

}
