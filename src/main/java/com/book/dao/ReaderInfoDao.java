package com.book.dao;

import com.book.pojo.ReaderInfo;

import java.util.List;

public interface ReaderInfoDao {
    int addReaderInfo(ReaderInfo readerInfo);

    ReaderInfo findById(Long readerId);

    List<ReaderInfo> findReaders();

    int updateReader(ReaderInfo readerInfo);

    int updatePass(Integer reader_id, String newPasswd);
}
