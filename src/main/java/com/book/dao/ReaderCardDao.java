package com.book.dao;

import com.book.pojo.ReaderCard;

import java.util.List;

public interface ReaderCardDao {
    ReaderCard login(ReaderCard readerCard);

    List<ReaderCard> queryCard();

    int limitCard(Long readerId);

    int unLimitCard(Long readerId);

    ReaderCard findById(Long readerId);
}
