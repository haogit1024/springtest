package com.czh.controller;

import com.czh.entity.Book;
import com.czh.entity.User;
import com.czh.response.StatusCode;
import com.czh.service.BookService;
import com.czh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by czh on 17-7-16.
 */
@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    private final static Logger log = Logger.getLogger(BookController.class);

    @RequestMapping(value = "/getBooks", method = RequestMethod.GET)
    public ModelAndView getBookByUid(@RequestParam String account, HttpSession session){
        ModelAndView model = new ModelAndView();
        if (null == account || "".equals(account)) {
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
            return model;
        }
        String sessionUid = (String) session.getAttribute("uid");
        if (null == sessionUid || "".equals(sessionUid)){
            User user = userService.getUser(account);
            if (null == user) {
                model.addObject("status",1);
                model.addObject("statusCode", StatusCode.OBJECTNOTEXIST.toString());
            } else {
                String uid = user.getId();
                List<Book> books = bookService.getBookByUid(uid);
                model.addObject("status", 0);
                model.addObject("books", books);
            }
        } else {
            List<Book> books = bookService.getBookByUid(sessionUid);
            model.addObject("status", 0);
            model.addObject("books", books);
        }
        return model;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insertBook(@RequestParam String account, @RequestParam String name, @RequestParam String budget, HttpSession session){
        ModelAndView model = new ModelAndView();
        if (null == account || "".equals(account) || null == name || "".equals(name) || null == budget || "".equals(budget)) {
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
            return model;
        }
        String sessionUid = (String) session.getAttribute("uid");
        Book book = new Book();
        book.setName(name);
        book.setBudget(Double.valueOf(budget));
        if (null == sessionUid || "".equals(sessionUid)) {
            User user = userService.getUser(account);
            if (null == user) {
                model.addObject("status",1);
                model.addObject("stausCode", StatusCode.OBJECTNOTEXIST.toString());
                return model;
            }
            book.setUid(user.getId());
        } else {
            book.setUid(sessionUid);
        }
        int id = bookService.insertBook(book);
        if (id < 0){
            model.addObject("status", 1);
            model.addObject("statusCode", StatusCode.FAILOPERATIONDATABASE.toString());
        } else {
            model.addObject("status",0);
            model.addObject("book", book);
        }
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateBook(@RequestParam String bid, @RequestParam String name, @RequestParam String budget){
        ModelAndView model = new ModelAndView();
        if (null == name || "".equals(name) || null == budget || "".equals(budget)) {
            model.addObject("status",1);
            model.addObject("statusCode", StatusCode.NULLREQUESTPARAMETER.toString());
            return model;
        }
        Book book = bookService.getBookById(Integer.valueOf(bid));
        if (null == book) {
            log.error("book对象不存在或为null");
            model.addObject("status",1);
            model.addObject("statusCode", StatusCode.OBJECTNOTEXIST);
            return model;
        }
        book.setName(name);
        book.setBudget(Double.valueOf(budget));
        boolean flag = bookService.updateBook(book);
        if (flag) {
            model.addObject("status",0);
            model.addObject("book", book);
        } else {
            log.info("book更新失败");
            model.addObject("status",1);
            model.addObject("statusCode",StatusCode.FAILOPERATIONDATABASE.toString());
        }
        return model;
    }
}
