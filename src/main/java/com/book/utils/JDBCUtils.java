package com.book.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//编写获取连接池的方法
public class JDBCUtils {
    //获得c3p0连接池对象
    private static ComboPooledDataSource ds = new ComboPooledDataSource();
    //获得数据库连接对象
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    //获得c3p0连接池对象
    public static DataSource getDataSource(){
        return ds;
    }
}
