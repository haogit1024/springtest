package com.czh.dao;

import com.czh.entity.Tickler;
import com.czh.mapper.TicklerMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by czh on 17-7-4.
 */
@Repository
public class TicklerDao {

//    private TicklerMapper ticklerMapper;
//
//    public TicklerDao(TicklerMapper ticklerMapper) {
//        this.ticklerMapper = ticklerMapper;
//    }

    private SqlSession session;

    public TicklerDao(SqlSession session) {
        this.session = session;
    }

    public Tickler getTicklerById(int id) {
        return this.session.selectOne("getTicklerById", id);
    }
    public List<Tickler> getTicklerByBid(int bid) {
        return this.session.selectList("getTicklerByBid", bid);
    }

    public int insertTickler(Tickler tickler) {
        int i = session.insert("insertTickler", tickler);
        if (i > 0) {
            return tickler.getId();
        }
        return -1;
    }

    public boolean updateTick(Tickler tickler) {
        int i = this.session.update("updateTickler", tickler);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
