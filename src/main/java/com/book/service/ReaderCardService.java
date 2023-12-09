package com.book.service;

import com.book.pojo.ReaderCard;

import java.util.List;

public interface ReaderCardService {
    ReaderCard login(ReaderCard readerCard);

    List<ReaderCard> queryCard();

    int limitCard(Long reader_id);

    int unLimitCard(Long aLong);

    ReaderCard findById(Long aLong);
}
