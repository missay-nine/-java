package com.book.dao;

import com.book.pojo.LendList;

import java.util.List;

public interface LendListDao {
    int addLend(LendList lendList);

    List<LendList> queryMyLendBooks(Long aLong);

    int adminBackBook(String bookId, String sernum);

    int deleteBookRecord(String sernum);

    List<LendList> queryLendList(String readId);
}
