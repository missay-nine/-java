package com.book.dao;

import com.book.pojo.BookInfo;
import com.book.pojo.ClassInfo;

import java.util.List;

public interface BookInfoDao {
    BookInfo findById(Long bookId);

    int deleteById(Long bookId);

    int addBook(BookInfo bookInfo);



    int updateBook(BookInfo bookInfo);

    List<ClassInfo> findBookClass();

    List<BookInfo> queryBook(String name);

    int updateBookState(int book_id,int state);
}
