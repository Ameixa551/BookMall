package com.example.bookmall.Models;

import java.util.Date;

public class Order {
    private int id;
    private Date CartTime;
    private Date PayTime;
    private Boolean isPaied;
    private float sumPrice;
    private int bookId;
    private int bookNum;
    private int uId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCartTime() {
        return CartTime;
    }

    public void setCartTime(Date cartTime) {
        CartTime = cartTime;
    }

    public Date getPayTime() {
        return PayTime;
    }

    public void setPayTime(Date payTime) {
        PayTime = payTime;
    }

    public Boolean getPaied() {
        return isPaied;
    }

    public void setPaied(Boolean paied) {
        isPaied = paied;
    }

    public float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }
}

