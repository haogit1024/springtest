package com.czh.controller;

import com.czh.entity.Record;
import com.czh.response.StatusCode;
import com.czh.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by czh on 17-7-21.
 */
@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "/getRecord", method = RequestMethod.GET)
    public ModelAndView getTicklerRecord(@RequestParam String tid){
        ModelAndView model = new ModelAndView();
        if (null == tid || "".equals(tid)) {
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
        }
        int ticklerId = 0;
        try {
            ticklerId = Integer.parseInt(tid);
        } catch (Exception e) {
            model.addObject("status",1);
            model.addObject("statusCode", StatusCode.NOTNUMBERPARAMETER.toString());
            return model;
        }
        List<Record> records = recordService.getRecordByTid(ticklerId);
        model.addObject("status", 0);
        model.addObject("records", records);
        return model;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insertRecord(@RequestParam String tid, @RequestParam String time, @RequestParam String grade, @RequestParam String money){
        ModelAndView model = new ModelAndView();
        if (null == tid || "".equals(tid) || null == time || "".equals(time) || null == grade || "".equals(grade) || null == grade || "".equals(grade)) {
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
            return model;
        }
        Record record = new Record();
        record.setTid(Integer.valueOf(tid));
        record.setMoney(Float.valueOf(money));
        record.setGrade(Integer.valueOf(grade));
        record.setTime(Integer.valueOf(time));
        record.setCreateTime((int)new Date().getTime());
        int rid = recordService.insertRecord(record);
        if (rid > 0) {
            model.addObject("status",0);
            model.addObject("id", rid);
        } else {
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.FAILOPERATIONDATABASE.toString());
        }
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateRecord(@RequestParam String tid, @RequestParam String time, @RequestParam String grade, @RequestParam String money){
        ModelAndView model = new ModelAndView();
        if (null == tid || "".equals(tid) || null == time || "".equals(time) || null == grade || "".equals(grade) || null == grade || "".equals(grade)) {
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
            return model;
        }
        Record record = new Record();
        record.setTid(Integer.valueOf(tid));
        record.setMoney(Float.valueOf(money));
        record.setGrade(Integer.valueOf(grade));
        record.setTime(Integer.valueOf(time));
        record.setCreateTime((int)new Date().getTime());
        boolean flag = recordService.updateRecord(record);
        if (flag) {
            model.addObject("status",0);
        } else {
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.FAILOPERATIONDATABASE.toString());
        }
        return model;
    }
}
