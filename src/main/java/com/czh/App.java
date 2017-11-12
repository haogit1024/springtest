package com.czh;



import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by czh on 17-6-4.
 */
@SpringBootApplication
@MapperScan("com.czh.mapper")
public class App extends SpringBootServletInitializer {
    private static final Properties PROPERTIES = new Properties();
    public static final String DOMAIN;
    public static final String SECRET;
    public static final String ISS;
    public static final String PROJECT_NAME;

    private static final Logger log = Logger.getLogger(App.class);
    static {
        InputStream appis = App.class.getResourceAsStream("/setting.properties");
        try {
            PROPERTIES.load(appis);
        } catch (IOException e) {
            log.debug("********加载properties文件出错**********");
            e.printStackTrace();
        }
        DOMAIN = PROPERTIES.getProperty("domain");
        SECRET = PROPERTIES.getProperty("secret");
        ISS = PROPERTIES.getProperty("iss");
        PROJECT_NAME = PROPERTIES.getProperty("project.name");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
