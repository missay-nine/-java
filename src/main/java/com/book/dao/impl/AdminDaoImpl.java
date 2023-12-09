package com.book.dao.impl;

import com.book.dao.AdminDao;
import com.book.pojo.Admin;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDaoImpl extends BaseDao implements AdminDao {

    @Override
    public Admin login(Admin admin) {
        try {
            Admin loginAdmin = queryRunner.query("select * from admin where admin_id=? and password=?", new BeanHandler<Admin>(Admin.class), admin.getAdmin_id(), admin.getPassword());
            return loginAdmin;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public int updatePassWord(Integer admin_id, String newPasswd) {
        try {
            return queryRunner.update("update admin set password=? where admin_id=?",newPasswd,admin_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return 0;
    }
}





