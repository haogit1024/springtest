package com.czh.controller;

import com.czh.dao.CzhTestDao;
import com.czh.database.DynamicDataSourceContextHolder;
import com.czh.dto.FileDTO;
import com.czh.entity.CzhTest;
import com.czh.entity.UserEntity;
import com.czh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Created by czh on 17-6-4.
 */
@RestController
public class TestController {
    @Autowired
    CzhTestDao czhTestDao;

    @RequestMapping("/test")
    public Object test(@RequestBody @Validated FileDTO fileDTO) {
//        DynamicDataSourceContextHolder.setDataSources("dev");
        /*List<CzhTest> list = czhTestDao.listCzhTest();
        list.forEach(System.out::println);
        return list;*/

        return fileDTO;
    }

}
