package com.book.service;


import com.book.pojo.Admin;

public interface AdminService {

    /**
     * 01-管理员登录
     * @param admin
     * @return
     */
    Admin login(Admin admin);

    int updatePassWord(Integer admin_id, String newPasswd);
}
