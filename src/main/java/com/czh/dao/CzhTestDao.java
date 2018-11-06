package com.czh.dao;

import com.czh.entity.CzhTest;
import com.czh.mapper.CzhTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CzhTestDao {
    @Autowired
    private CzhTestMapper czhTestMapper;

    public List<CzhTest> listCzhTest() {
        return czhTestMapper.getCzhTest();
    }
}
