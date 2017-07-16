package com.czh.controller;

import com.czh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by czh on 17-7-16.
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;


}
