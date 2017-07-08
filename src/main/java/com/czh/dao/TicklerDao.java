package com.czh.dao;

import com.czh.entity.Tickler;
import com.czh.mapper.TicklerMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

    public Tickler getTickler(int id) {
        return this.session.selectOne("getTickler", id);
    }

    public int insertTickler(Tickler tickler) {
        return session.insert("addTickler", tickler);
    }
}
