package com.book.dao.impl;

import com.book.dao.ReaderInfoDao;
import com.book.pojo.ReaderInfo;
import com.book.utils.JDBCUtils;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReaderInfoDaoImpl extends BaseDao implements ReaderInfoDao {
    @SneakyThrows
    @Override
    public int addReaderInfo(ReaderInfo readerInfo) {

       // 这里跟前面有点不一样，因为当时只是填充书 没有涉及两个表
        // 现在涉及两个表  又没有外键，只能 用事物写，先定义一个链接
        // 之前为什么不用连接，是因为我们用了工具类 可以自动连接、
        Connection connection = null;
        try{
            connection = JDBCUtils.getDataSource().getConnection();
           // 关闭自动提交
            connection.setAutoCommit(false);
            //  第一个表的预处理sql
            PreparedStatement ps1 = connection.prepareStatement("insert into reader_info values (?,?,?,?,?,?)");
            ps1.setObject(1,readerInfo.getReader_id());
            ps1.setObject(2,readerInfo.getName());
            ps1.setObject(3,readerInfo.getSex());
            ps1.setObject(4,readerInfo.getBirth());
            ps1.setObject(5,readerInfo.getAddress());
            ps1.setObject(6,readerInfo.getTelcode());

            // 新增reader_info
            int result1 = ps1.executeUpdate();

            // 第二个表的预处理sql
            PreparedStatement ps2 = connection.prepareStatement("insert into reader_card values (?,?,?,?)");
            ps2.setObject(1,readerInfo.getReader_id());
            ps2.setObject(2,readerInfo.getName());
            ps2.setObject(3,666666);
            // 为什么要写死 ，因为读者不能自己注册账号，我这里初始化全部都设置为六个6，需要改密码自己再改
            // 就类似学校学生校园卡的密码一样
            ps2.setObject(4,1);
            int result2 = ps2.executeUpdate();

            connection.commit();

            return result1 + result2;
        }catch (Exception e) {
            try {
                // e.printStackTrace();
                // 如果出现异常，就回滚
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
            }
            return 0;
        }

    }

    @Override
    public ReaderInfo findById(Long readerId) {
        try{
            return queryRunner.query("select * from reader_info where reader_id=?",new BeanHandler<ReaderInfo>(ReaderInfo.class),readerId);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
        return null;
    }

    @Override
    public List<ReaderInfo> findReaders() {
        try{
            return queryRunner.query("select * from reader_info",new BeanListHandler<ReaderInfo>(ReaderInfo.class));
        }catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
        return null;
    }

    @Override
    public int updateReader(ReaderInfo readerInfo) {
        try{
            return queryRunner.update("update reader_info set name=?,sex=?,birth=?,address=?,telcode=? where reader_id=?",
                    readerInfo.getName(),readerInfo.getSex(),readerInfo.getBirth(),readerInfo.getAddress(),readerInfo.getTelcode(),readerInfo.getReader_id());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
        return 0;
    }

    @Override
    public int updatePass(Integer reader_id, String newPasswd) {
        try {
            return queryRunner.update("update reader_card set passwd=? where reader_id=?",newPasswd,reader_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return 0;
    }
}
