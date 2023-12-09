package com.book.service.impl;

import com.book.dao.BookInfoDao;
import com.book.dao.impl.BookInfoDaoImpl;
import com.book.pojo.BookInfo;
import com.book.pojo.ClassInfo;
import com.book.service.BookInfoService;

import java.util.List;

public class BookInfoServiceImpl implements BookInfoService {

    BookInfoDao bookInfoDao = new BookInfoDaoImpl();


    @Override
    public BookInfo findById(Long book_id) {
        return bookInfoDao.findById(book_id);
    }

    @Override
    public int deleteById(Long book_id) {
        return bookInfoDao.deleteById(book_id);
    }

    @Override
    public int addBook(BookInfo bookInfo) {
        return bookInfoDao.addBook(bookInfo);
    }

    @Override
    public int updateBook(BookInfo bookInfo) {
        return bookInfoDao.updateBook(bookInfo);
    }

    @Override
    public List<ClassInfo> findBookClass() {
        return bookInfoDao.findBookClass();
    }

    @Override
    public List<BookInfo> queryBook(String name) {
        return bookInfoDao.queryBook(name);
    }


    @Override
    public int updateBookState(int book_id, int state) {
        return bookInfoDao.updateBookState(book_id,state);
    }

}
