package com.czh.utiltest;

import com.czh.dao.TicklerDao;
import com.czh.entity.Tickler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
//        Tickler tickler = ticklerDao.getTicklerById(12);
//        System.out.println(tickler.getId());
        List<Tickler> list = ticklerDao.getTicklerByBid(1);
        System.out.println(list.size());
    }

    @Test
    public void testInsert(){
        Tickler tickler = new Tickler();
        tickler.setInputTime(121121);
        tickler.setRemark("fuckyou");
        tickler.setStatus(1);
        tickler.setTime(111);
        tickler.setBid(1);
        int i = ticklerDao.insertTickler(tickler);
        System.out.println(i);
    }

    @Test
    public void testUpdate(){
        Tickler tickler = new Tickler();
        tickler.setInputTime(1);
        tickler.setRemark("aaa");
        tickler.setStatus(12);
        tickler.setTime(11);
        tickler.setBid(1);
        tickler.setId(12);
        boolean b = ticklerDao.updateTick(tickler);
        System.out.println(b);
    }
}
