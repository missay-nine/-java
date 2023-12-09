package com.book.pojo;

public class ReaderInfo {
    private long reader_id;
    private String name;
    private String sex;
    private String birth;
    private String address;
    private String telcode;

    public ReaderInfo() {
    }

    public ReaderInfo(long reader_id, String name, String sex, String birth, String address, String telcode) {
        this.reader_id = reader_id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.address = address;
        this.telcode = telcode;
    }

    /**
     * 获取
     * @return reader_id
     */
    public long getReader_id() {
        return reader_id;
    }

    /**
     * 设置
     * @param reader_id
     */
    public void setReader_id(long reader_id) {
        this.reader_id = reader_id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取
     * @return birth
     */
    public String getBirth() {
        return birth;
    }

    /**
     * 设置
     * @param birth
     */
    public void setBirth(String birth) {
        this.birth = birth;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取
     * @return telcode
     */
    public String getTelcode() {
        return telcode;
    }

    /**
     * 设置
     * @param telcode
     */
    public void setTelcode(String telcode) {
        this.telcode = telcode;
    }

    public String toString() {
        return "ReaderInfo{reader_id = " + reader_id + ", name = " + name + ", sex = " + sex + ", birth = " + birth + ", address = " + address + ", telcode = " + telcode + "}";
    }
}
