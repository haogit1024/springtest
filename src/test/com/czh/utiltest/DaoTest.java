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
//        User u = userDao.getUser("tese1");
        User user = new User();
//        user.setId(u.getId());
        user.setAccount("tese11");
        user.setEmail("testemail.com1");
        user.setGender(1);
        user.setNickname("testnick1");
        user.setPassword("test11");
        user.setPhone("1234567");
        user.setRealname("testReal1");
        user.setId("testid");
//        userDao.insertUser(user);
//        userDao.getUser("admin");
        boolean b = userDao.updateUser(user);
        System.out.println(b);
    }

}
