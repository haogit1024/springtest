package com.czh.utiltest;

import com.czh.dao.BookDao;
import com.czh.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by czh on 17-7-16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookTest {
    @Autowired
    private BookDao bookDao;

    @Test
    public void testGet(){
//        Book book = bookDao.getBookById(1);
//        System.out.println(book);
        List<Book> books = bookDao.getBookByUid("fuck");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testInsert(){
        Book book = new Book();
        book.setBudget(1.2);
        book.setName("name");
        book.setUid("fuck");
        int i = bookDao.insertBook(book);
        System.out.println(i);
    }

    @Test
    public void testUpdate(){
        Book book = new Book();
        book.setBudget(1.2);
        book.setName("name");
        book.setUid("fuckyou");
        book.setId(1);
        boolean flag = bookDao.updateBook(book);
        if (flag){
            System.out.println(bookDao.getBookById(1));
        }
    }
}
