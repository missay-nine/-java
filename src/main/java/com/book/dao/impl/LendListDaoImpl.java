package com.book.dao.impl;

import com.book.dao.LendListDao;
import com.book.pojo.LendList;
import com.book.utils.JDBCUtils;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LendListDaoImpl extends  BaseDao implements LendListDao {
    @Override
    public int addLend(LendList lendList) {
        try {
            return queryRunner.update("insert into lend_list values (null,?,?,now(),null)", lendList.getBook_id(), lendList.getReader_id());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return 0;
    }

    @Override
    public List<LendList> queryMyLendBooks(Long aLong) {
        try{
            return queryRunner.query("select * from lend_list where reader_id=?",new BeanListHandler<LendList>(LendList.class),aLong);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    @Override
    public int adminBackBook(String book_id, String sernum) {
       // 这里涉及两个表 需要
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps1 = connection.prepareStatement("update lend_list set back_date=now() where sernum=" + sernum);
            int count1 = ps1.executeUpdate();

            PreparedStatement ps2 = connection.prepareStatement("update book_info set state=1 where book_id=" + book_id);
            int count2 = ps2.executeUpdate();

            //手动提交
            connection.commit();
            return count1+count2;

        } catch (SQLException e) {
            try {
                //回顾异常
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
            }
        } finally {
        }
        return 0;
    }

    @Override
    public int deleteBookRecord(String sernum) {
        System.out.println("sernum="+sernum);
        try{
            // 你这里写一个reader_id 真的6
            return queryRunner.update("delete from lend_list where sernum=?",sernum);
    }catch (Exception e) {
        e.printStackTrace();
    } finally {
    }
        return 0;
    }

    @Override
    public List<LendList> queryLendList(String readId) {
        try{
            if (readId.equals("")) {
                return queryRunner.query("select * from lend_list", new BeanListHandler<LendList>(LendList.class));
            } else {
                return queryRunner.query("select * from lend_list where reader_id like ?", new BeanListHandler<LendList>(LendList.class), "%" + readId + "%");
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }
}
