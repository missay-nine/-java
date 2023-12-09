package com.book.dao.impl;

import com.book.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

public class BaseDao {
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
}
