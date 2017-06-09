//package com.czh;
//
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
///**
// * Created by czh on 17-6-9.
// */
//@Configuration
////@MapperScan("com.czh.mapper")
//public class BeanCiguration {
//    private static final Properties PROPERTIES = new Properties();
//    static {
//        InputStream is = BeanCiguration.class.getResourceAsStream("/application.properties");
//        try {
//            PROPERTIES.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @Bean
//    public DataSource dataSource() {
//        String url = PROPERTIES.getProperty("spring.datasource.url");
//        String username = PROPERTIES.getProperty("spring.datasource.username");
//        String password = PROPERTIES.getProperty("spring.datasource.password");
//        return new DriverManagerDataSource(url, username, password);
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        return sessionFactory.getObject();
//    }
//}
