package com.book.service;

import com.book.pojo.ReaderInfo;

import java.util.List;

public interface ReadInfoService {
    int addReader(ReaderInfo readerInfo);

    ReaderInfo findById(Long aLong);

    List<ReaderInfo> findReaders();

    int updateReader(ReaderInfo readerInfo);

    int updatePass(Integer reader_id, String newPasswd);
}
