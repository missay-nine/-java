package com.book.pojo;

public class ClassInfo {
    private Long class_id;
    private String class_name;

    public ClassInfo() {
    }

    public ClassInfo(Long class_id, String class_name) {
        this.class_id = class_id;
        this.class_name = class_name;
    }

    /**
     * 获取
     * @return class_id
     */
    public Long getClass_id() {
        return class_id;
    }

    /**
     * 设置
     * @param class_id
     */
    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    /**
     * 获取
     * @return class_name
     */
    public String getClass_name() {
        return class_name;
    }

    /**
     * 设置
     * @param class_name
     */
    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String toString() {
        return "ClassInfo{class_id = " + class_id + ", class_name = " + class_name + "}";
    }
}
