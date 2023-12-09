package com.book.pojo;

public class Admin {
    private Integer admin_id;
    private String password;

    public Admin() {

    }

    public Admin(Integer admin_id, String password) {
        this.admin_id = admin_id;
        this.password = password;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", password='" + password + '\'' +
                '}';
    }
}
