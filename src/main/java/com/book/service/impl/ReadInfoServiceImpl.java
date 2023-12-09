package com.book.service.impl;

import com.book.dao.ReaderInfoDao;
import com.book.dao.impl.ReaderInfoDaoImpl;
import com.book.pojo.ReaderInfo;
import com.book.service.ReadInfoService;

import java.util.List;

public class ReadInfoServiceImpl implements ReadInfoService {

    //初始化一个接口层
    ReaderInfoDao readInfoDao = new ReaderInfoDaoImpl();
    @Override
    public int addReader(ReaderInfo readerInfo) {
        return readInfoDao.addReaderInfo(readerInfo);
    }

    @Override
    public ReaderInfo findById(Long reader_id) {
        return readInfoDao.findById(reader_id);
    }

    @Override
    public List<ReaderInfo> findReaders() {
        return readInfoDao.findReaders();
    }

    @Override
    public int updateReader(ReaderInfo readerInfo) {
        return readInfoDao.updateReader(readerInfo);
    }

    @Override
    public int updatePass(Integer reader_id, String newPasswd) {
        return readInfoDao.updatePass(reader_id,newPasswd);
    }
}
