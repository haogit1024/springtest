package com.czh.database;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LogManager.getLogger(DynamicDataSourceContextHolder.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String obj = DynamicDataSourceContextHolder.getDataSources();
        log.info("dataSource:" + obj);
        return obj;
//        log.info("返回dev数据库");
//        return "dev";
    }
}
