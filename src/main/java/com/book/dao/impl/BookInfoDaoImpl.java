package com.book.dao.impl;

import com.book.dao.BookInfoDao;
import com.book.pojo.BookInfo;
import com.book.pojo.ClassInfo;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BookInfoDaoImpl extends BaseDao implements BookInfoDao {


    @Override
    public BookInfo findById(Long bookId) {
        try{
            return queryRunner.query("select * from book_info where book_id = ?",new BeanHandler<>(BookInfo.class),bookId);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {

        }
        return null;
    }

    @Override
    public int deleteById(Long book_id) {
        try{
            BookInfo bookInfo = this.findById(book_id);
            if (bookInfo.getState() == 1){
                return queryRunner.update("delete from book_info where book_id = ?",book_id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return 0;
    }

    @Override
    public int addBook(BookInfo bookInfo) {
        try{
            return queryRunner.update("insert into book_info values (null,?,?,?,?,?,?,?,?,?,?,1)",
                    bookInfo.getName(),
                    bookInfo.getAuthor(),
                    bookInfo.getPublish(),
                    bookInfo.getIsbn(),
                    bookInfo.getIntroduction(),
                    bookInfo.getLanguage(),
                    bookInfo.getPrice(),
                    bookInfo.getPubdate(),
                    bookInfo.getClass_id(),
                    bookInfo.getPressmark());
        }catch (SQLException e){
            e.printStackTrace();
        }finally {

        }
        return 0;
    }

    @Override
    public int updateBook(BookInfo bookInfo) {
        try {
          return queryRunner.update("update book_info set name=?,author=?,publish=?,isbn=?,introduction=?,language=?,price=?,pubdate=?,class_id=?,pressmark=? where book_id=?",
                  bookInfo.getName(),
                  bookInfo.getAuthor(),
                  bookInfo.getPublish(),
                  bookInfo.getIsbn(),
                  bookInfo.getIntroduction(),
                  bookInfo.getLanguage(),
                  bookInfo.getPrice(),
                  bookInfo.getPubdate(),
                  bookInfo.getClass_id(),
                  bookInfo.getPressmark(),
                  bookInfo.getBook_id());
        }catch (SQLException e){
            e.printStackTrace();
        }finally{

        }
        return 0;
    }

    @Override
    public List<ClassInfo> findBookClass() {
        try {
            return queryRunner.query("select * from class_info", new BeanListHandler<ClassInfo>(ClassInfo.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return null;

    }

    @Override
    public List<BookInfo> queryBook(String name) {
        try{
            return queryRunner.query("select * from book_info where name like ?",new BeanListHandler<BookInfo>(BookInfo.class),"%"+name+"%");
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
      return null;
    }

    @Override
    public int updateBookState(int book_id, int state) {
        try {
            return queryRunner.update("update book_info set state=? where book_id=?",
                    state, book_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return 0;
    }
}
