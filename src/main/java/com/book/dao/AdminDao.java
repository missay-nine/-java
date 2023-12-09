package com.book.dao;

import com.book.pojo.Admin;

public interface AdminDao {
//    管理员登录
    Admin login(Admin admin);

    int updatePassWord(Integer adminId, String newPasswd);
}
