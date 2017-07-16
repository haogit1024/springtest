package com.czh.dao;

import com.czh.entity.Book;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by czh on 17-7-15.
 */
@Repository
public class BookDao {
    private SqlSession session;

    public BookDao(SqlSession session) {
        this.session = session;
    }

    public Book getBookById(int id){
        return this.session.selectOne("getBookById", id);
    }

    public List<Book> getBookByUid(String uid) {
        return this.session.selectList("getBookByUid", uid);
    }

    public int insertBook(Book book) {
        int i = this.session.insert("insertBook", book);
        if (i > 0) {
            return book.getId();
        }
        return -1;
    }

    public boolean updateBook(Book book) {
        int i = this.session.update("updateBook", book);
        return i > 0;
    }
}
