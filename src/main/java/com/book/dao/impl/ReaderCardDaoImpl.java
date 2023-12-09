package com.book.dao.impl;

import com.book.dao.ReaderCardDao;
import com.book.pojo.ReaderCard;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ReaderCardDaoImpl extends  BaseDao implements ReaderCardDao {
    @Override
    public ReaderCard login(ReaderCard card) {
        try {
            return queryRunner.query("select * from reader_card where reader_id=? and passwd=? and card_state=1", new BeanHandler<ReaderCard>(ReaderCard.class), card.getReader_id(),
                    card.getPasswd());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    @Override
    public List<ReaderCard> queryCard() {
        try {
            return queryRunner.query("select * from reader_card", new BeanListHandler<ReaderCard>(ReaderCard.class));
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return null;
    }

    @Override
    public int limitCard(Long readerId) {
        try{
            return queryRunner.update("update reader_card set card_state=0 where reader_id=?",readerId);
        }catch (SQLException e) {
            e.printStackTrace();
    }finally {
        }
        return 0;
        }

    @Override
    public int unLimitCard(Long readerId) {
        try{
            return queryRunner.update("update reader_card set card_state=1 where reader_id=?",readerId);
        }catch (SQLException e) {
            e.printStackTrace();
    }finally {
        }
        return 0;
        }

    @Override
    public ReaderCard findById(Long readerId) {
        try {
            return queryRunner.query("select * from reader_card where reader_id=?", new BeanHandler<ReaderCard>(ReaderCard.class), readerId);
        } catch (SQLException e) {
        } finally {
        }
        return null;
    }
}
