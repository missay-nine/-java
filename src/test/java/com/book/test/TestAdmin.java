package com.book.test;

import com.book.dao.AdminDao;
import com.book.dao.impl.AdminDaoImpl;
import com.book.pojo.Admin;
import org.junit.Test;

public class TestAdmin {
    AdminDao dao = new AdminDaoImpl();
    @Test
    public  void testLogin(){
       Admin admin = new Admin(9527,"008");
        Admin login = dao.login(admin);
        if (login!=null){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }
}
