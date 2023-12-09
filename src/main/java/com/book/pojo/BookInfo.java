package com.book.pojo;

public class BookInfo {
    private long book_id;
    private String name;
    private String author;
    private String publish;
    private String isbn;
    private String introduction;
    private String language;
    private double price;
    private String pubdate;  // Mysql中日期使用String 更方便
    private long class_id;
    private long pressmark;
    private long state;

    public BookInfo() {
    }

    public BookInfo(String name, String author, String publish, String isbn, String introduction, String language, double price, String pubdate, long class_id, long pressmark) {
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.isbn = isbn;
        this.introduction = introduction;
        this.language = language;
        this.price = price;
        this.pubdate = pubdate;
        this.class_id = class_id;
        this.pressmark = pressmark;
    }

    /**
     * 获取
     * @return book_id
     */
    public long getBook_id() {
        return book_id;
    }

    /**
     * 设置
     * @param book_id
     */
    public void setBook_id(long book_id) {
        this.book_id = book_id;
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
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取
     * @return publish
     */
    public String getPublish() {
        return publish;
    }

    /**
     * 设置
     * @param publish
     */
    public void setPublish(String publish) {
        this.publish = publish;
    }

    /**
     * 获取
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * 设置
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * 获取
     * @return introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置
     * @param introduction
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 获取
     * @return pubdate
     */
    public String getPubdate() {
        return pubdate;
    }

    /**
     * 设置
     * @param pubdate
     */
    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    /**
     * 获取
     * @return class_id
     */
    public long getClass_id() {
        return class_id;
    }

    /**
     * 设置
     * @param class_id
     */
    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    /**
     * 获取
     * @return pressmark
     */
    public long getPressmark() {
        return pressmark;
    }

    /**
     * 设置
     * @param pressmark
     */
    public void setPressmark(long pressmark) {
        this.pressmark = pressmark;
    }

    /**
     * 获取
     * @return state
     */
    public long getState() {
        return state;
    }

    /**
     * 设置
     * @param state
     */
    public void setState(long state) {
        this.state = state;
    }

    public String toString() {
        return "BookInfo{book_id = " + book_id + ", name = " + name + ", author = " + author + ", publish = " + publish + ", isbn = " + isbn + ", introduction = " + introduction + ", language = " + language + ", price = " + price + ", pubdate = " + pubdate + ", class_id = " + class_id + ", pressmark = " + pressmark + ", state = " + state + "}";
    }
}
