package com.czh.database;

/**
 * @author czh
 * 数据库上下文
 * 持有一个ThreadLocal保存线程共享变量, spring aop根据改共享变量切换数据源
 */
public class DynamicDataSourceContextHolder {
    // 本地线程环境
    private static final ThreadLocal<String> dataSources = new ThreadLocal<>();

    // 设置数据源
    public static void setDataSources(String customerType) {
        dataSources.set(customerType);
    }

    public static String getDataSources() {
        return dataSources.get();
    }

    public static void clearDataSource() {
        dataSources.remove();
    }
}
