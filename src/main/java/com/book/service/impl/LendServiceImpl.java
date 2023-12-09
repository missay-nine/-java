package com.book.service.impl;


import com.book.dao.LendListDao;
import com.book.dao.impl.LendListDaoImpl;
import com.book.pojo.LendList;
import com.book.service.LendService;

import java.util.List;

public class LendServiceImpl implements LendService {

    // 定义一个dao层
    LendListDao lendListDao = new LendListDaoImpl();
    @Override
    public int addLend(LendList lendList) {
        return  lendListDao.addLend(lendList);
    }

    @Override
    public List<LendList> queryMyLendBooks(Long aLong) {
        return lendListDao.queryMyLendBooks(aLong);
    }

    @Override
    public int adminBackBook(String bookId, String sernum) {
        return lendListDao.adminBackBook(bookId,sernum);
    }

    @Override
    public int deleteBookRecord(String sernum) {
        return lendListDao.deleteBookRecord(sernum);
    }

    @Override
    public List<LendList> queryLendList(String readId) {
        return lendListDao.queryLendList(readId);
    }
}