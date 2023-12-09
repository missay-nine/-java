package com.book.pojo;

public class LendList {
    private Long sernum;
    private Long book_id;
    private Long reader_id;
    private String lend_date;
    private String back_date;

    public LendList() {
    }

    public LendList(Long book_id, Long reader_id) {

        this.book_id = book_id;
        this.reader_id = reader_id;

    }

    /**
     * 获取
     * @return sernum
     */
    public Long getSernum() {
        return sernum;
    }

    /**
     * 设置
     * @param sernum
     */
    public void setSernum(Long sernum) {
        this.sernum = sernum;
    }

    /**
     * 获取
     * @return book_id
     */
    public Long getBook_id() {
        return book_id;
    }

    /**
     * 设置
     * @param book_id
     */
    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    /**
     * 获取
     * @return reader_id
     */
    public Long getReader_id() {
        return reader_id;
    }

    /**
     * 设置
     * @param reader_id
     */
    public void setReader_id(Long reader_id) {
        this.reader_id = reader_id;
    }

    /**
     * 获取
     * @return lend_date
     */
    public String getLend_date() {
        return lend_date;
    }

    /**
     * 设置
     * @param lend_date
     */
    public void setLend_date(String lend_date) {
        this.lend_date = lend_date;
    }

    /**
     * 获取
     * @return back_date
     */
    public String getBack_date() {
        return back_date;
    }

    /**
     * 设置
     * @param back_date
     */
    public void setBack_date(String back_date) {
        this.back_date = back_date;
    }

    public String toString() {
        return "LendList{sernum = " + sernum + ", book_id = " + book_id + ", reader_id = " + reader_id + ", lend_date = " + lend_date + ", back_date = " + back_date + "}";
    }
}
