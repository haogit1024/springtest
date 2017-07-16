package com.czh.service;

import com.czh.dao.BookDao;
import com.czh.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by czh on 17-7-16.
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public Book getBookById(int id){
        return bookDao.getBookById(id);
    }

    public List<Book> getBookByUid(String uid) {
        return bookDao.getBookByUid(uid);
    }

    public int insertBook(Book book) {
        return bookDao.insertBook(book);
    }

    public boolean updateBook(Book book) {
        return bookDao.updateBook(book);
    }
}
