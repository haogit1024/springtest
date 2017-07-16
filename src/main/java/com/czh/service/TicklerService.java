package com.czh.service;

import com.czh.dao.TicklerDao;
import com.czh.entity.Tickler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by czh on 17-7-8.
 */
@Service
public class TicklerService {
    @Autowired
    private TicklerDao ticklerDao;

    public Tickler getTicklerById(int id) {
        return ticklerDao.getTicklerById(id);
    }

    public List<Tickler> getTicklerByBid(int bid) {
        return ticklerDao.getTicklerByBid(bid);
    }

    public int insertTickler(Tickler tickler) {
        return ticklerDao.insertTickler(tickler);
    }

    public boolean updateTickler(Tickler tickler) {
        return ticklerDao.updateTick(tickler);
    }
}
