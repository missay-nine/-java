package com.book.service;

import com.book.pojo.BookInfo;
import com.book.pojo.ClassInfo;

import java.util.List;

public interface BookInfoService {
    BookInfo findById(Long book_id);

    int deleteById(Long aLong);

    int addBook(BookInfo bookInfo);



    int updateBook(BookInfo bookInfo);

    List<ClassInfo> findBookClass();

    List<BookInfo> queryBook(String name);

    int updateBookState(int book_id,int state);
}
