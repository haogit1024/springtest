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
        user.setAccount("tese1");
        user.setEmail("testemail.com");
        user.setGender(1);
        user.setNickname("testnick");
        user.setPassword("test1");
        user.setPhone("12345678901");
        user.setRealname("testReal");
        user.setId("testid");
        userDao.insertUser(user);
//        userDao.getUser("admin");
    }

}
