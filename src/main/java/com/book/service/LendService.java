package com.book.service;

import com.book.pojo.LendList;

import java.util.List;

public interface LendService {
    int addLend(LendList lendList);

    List<LendList> queryMyLendBooks(Long aLong);

    int adminBackBook(String bookId, String sernum);

    int deleteBookRecord(String sernum);

    List<LendList> queryLendList(String readId);
}
