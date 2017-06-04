package com.czh.dao;

import com.czh.entity.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by czh on 17-6-4.
 */
@Repository
public class TestDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Access getAccess(int id) {
        Access access = jdbcTemplate.queryForObject("select * from access where id = ?", new RowMapper<Access>() {
            @Override
            public Access mapRow(ResultSet resultSet, int i) throws SQLException {
                Access access = new Access();
                access.setId(resultSet.getInt("id"));
                access.setJsapi(resultSet.getString("jsapi"));
                access.setToken(resultSet.getString("token"));
                access.setTime(resultSet.getTimestamp("time"));
                return access;
            }
        }, id);
        return access;
    }
}
