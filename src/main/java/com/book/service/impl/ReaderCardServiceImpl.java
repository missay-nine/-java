package com.book.service.impl;

import com.book.dao.ReaderCardDao;
import com.book.dao.impl.ReaderCardDaoImpl;
import com.book.pojo.ReaderCard;
import com.book.service.ReaderCardService;

import java.util.List;

public class ReaderCardServiceImpl implements ReaderCardService {

    ReaderCardDao readerCardDao=new ReaderCardDaoImpl();
    @Override
    public ReaderCard login(ReaderCard readerCard) {
       return readerCardDao.login(readerCard);
    }

    @Override
    public List<ReaderCard> queryCard() {
        return readerCardDao.queryCard();
    }

    @Override
    public int limitCard(Long reader_id) {
        return readerCardDao.limitCard(reader_id);
    }

    @Override
    public int unLimitCard(Long reader_id) {
        return readerCardDao.unLimitCard(reader_id);
    }

    @Override
    public ReaderCard findById(Long reader_id) {
        return readerCardDao.findById(reader_id);
    }
}
