package com.example.bookmall.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.io.Serializable;

public class Order extends BaseObservable implements Serializable {
    private int id;
    private long cartTime;
    private long payTime;
    private Boolean isPaid;
    private float sumPrice;
    private int bookId;
    private int bookNum;
    private int uid;

    public Order(int id, long cartTime, long payTime, Boolean isPaid, float sumPrice, int bookId, int bookNum, int uid) {
        this.id = id;
        this.cartTime = cartTime;
        this.payTime = payTime;
        this.isPaid = isPaid;
        this.sumPrice = sumPrice;
        this.bookId = bookId;
        this.bookNum = bookNum;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public long getCartTime() {
        return cartTime;
    }

    public void setCartTime(long cartTime) {
        this.cartTime = cartTime;
    }

    @Bindable
    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean paid) {
        isPaid = paid;
    }

    @Bindable
    public float getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uId) {
        this.uid = uId;
    }

    @Bindable
    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}

