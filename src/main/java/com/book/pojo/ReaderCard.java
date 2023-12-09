package com.book.pojo;

public class ReaderCard {
    private Integer reader_id;
    private String name;
    private String passwd;
    private Integer card_state;

    public ReaderCard() {
    }

    public ReaderCard(Integer reader_id, String name, String passwd, Integer card_state) {
        this.reader_id = reader_id;
        this.name = name;
        this.passwd = passwd;
        this.card_state = card_state;
    }

    /**
     * 获取
     * @return reader_id
     */
    public Integer getReader_id() {
        return reader_id;
    }

    /**
     * 设置
     * @param reader_id
     */
    public void setReader_id(Integer reader_id) {
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
     * @return passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * 设置
     * @param passwd
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * 获取
     * @return card_state
     */
    public Integer getCard_state() {
        return card_state;
    }

    /**
     * 设置
     * @param card_state
     */
    public void setCard_state(Integer card_state) {
        this.card_state = card_state;
    }

    public String toString() {
        return "ReaderCard{reader_id = " + reader_id + ", name = " + name + ", passwd = " + passwd + ", card_state = " + card_state + "}";
    }
}
