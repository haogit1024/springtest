package com.czh.utiltest;

import com.czh.dao.TicklerDao;
import com.czh.entity.Tickler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by czh on 17-7-8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicklerTest {
    @Autowired
    private TicklerDao ticklerDao;
    @Test
    public void testGet(){

    }

    @Test
    public void testInsert(){
        Tickler tickler = new Tickler();
        tickler.setInputTime(121121);
        tickler.setRemark("fuckyou");
        tickler.setStatus(1);
        tickler.setTime(111);
        tickler.setMoney(1.2f);
        tickler.setUid("fuckyou");
        int i = ticklerDao.insertTickler(tickler);
        System.out.println(i);
//        Tickler t = ticklerDao.getTickler(1);
//        System.out.println(t.getRemark());
    }
}
