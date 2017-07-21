package com.czh.controller;

import com.czh.entity.Record;
import com.czh.entity.Tickler;
import com.czh.entity.User;
import com.czh.response.StatusCode;
import com.czh.service.RecordService;
import com.czh.service.TicklerService;
import com.czh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by czh on 17-7-14.
 */
@Controller
@RequestMapping("/tickler")
public class TicklerController {
    @Autowired
    private TicklerService ticklerService;
    @Autowired
    private RecordService recordService;


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView getTickler(@RequestParam String bid, HttpSession session){
        ModelAndView model = new ModelAndView();
        if (null == bid || "".equals(bid)) {
            model.addObject("status",1);
            model.addObject("statusCode", "请求参数为空");
            return model;
        }
        int bookId = 0;
        try {
            bookId = Integer.parseInt(bid);
        } catch (NumberFormatException e) {
            model.addObject("status",1);
            model.addObject("statusCode", StatusCode.NOTNUMBERPARAMETER.toString());
            return model;
        }
        List<Tickler> list = ticklerService.getTicklerByBid(bookId);
        model.addObject("status", 0);
        model.addObject("ticklers", list);
        return model;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insertTickler(@RequestParam String account, @RequestParam int time, @RequestParam String remark, @RequestParam int bid , HttpSession session){
        ModelAndView model = new ModelAndView();
        if (account == null || "".equals(account) || null == remark || "".equals(remark) || bid == 0) {
            model.addObject("status",1);
            model.addObject("statusCode", "请求参数为空");
            return model;
        }
        Tickler tickler = new Tickler();
        tickler.setInputTime((int)new Date().getTime());
        tickler.setTime(time);
        tickler.setBid(bid);
        tickler.setStatus(1);
        int id = ticklerService.insertTickler(tickler);
        if (id < 0) {
            model.addObject("status",1);
            model.addObject("statusCode", StatusCode.FAILOPERATIONDATABASE.toString());
        }
        model.addObject("status",0);
        model.addObject("tid", tickler.getId());
        return model;
    }

//    @RequestMapping(value = "/getRecord", method = RequestMethod.GET)
//    public ModelAndView getTicklerRecord(@RequestParam String tid){
//        ModelAndView model = new ModelAndView();
//        if (null == tid || "".equals(tid)) {
//            model.addObject("status", 1);
//            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
//        }
//        int ticklerId = 0;
//        try {
//            ticklerId = Integer.parseInt(tid);
//        } catch (Exception e) {
//            model.addObject("status",1);
//            model.addObject("statusCode", StatusCode.NOTNUMBERPARAMETER.toString());
//            return model;
//        }
//        List<Record> records = recordService.getRecordByTid(ticklerId);
//        model.addObject("status", 0);
//        model.addObject("records", records);
//        return model;
//    }
}
