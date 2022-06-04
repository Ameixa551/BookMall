package com.example.bookmall.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.io.Serializable;

public class DisplayOrder extends BaseObservable implements Serializable {
    private int orderId;
    private long cartTime;
    private long payTime;
    private Boolean isPaid;
    private float sumPrice;
    private int bookId;
    private String name;
    private String coverPic;
    private float price;
    private String author;
    private String ISBN;
    private String description;
    private String category;
    private int bookNum;
    private int uid;
    private boolean isSelected;

    public DisplayOrder(Book book, Order order){
        this.orderId = order.getId();
        this.cartTime = order.getCartTime();
        this.payTime = order.getPayTime();
        this.isPaid = order.getIsPaid();
        this.sumPrice = order.getSumPrice();
        this.bookNum = order.getBookNum();
        this.uid = order.getUid();
        this.bookId = book.getId();
        this.name = book.getName();
        this.coverPic = book.getCoverPic();
        this.price = book.getPrice();
        this.author = book.getAuthor();
        this.ISBN = book.getISBN();
        this.description = book.getDescription();
        this.category = book.getCategory();
        this.isSelected = true;
    }

    public Order getOrder(){
        return new Order(orderId, cartTime, payTime, isPaid, sumPrice, bookId, bookNum, uid);
    }

    @Bindable
    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    @Bindable
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

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    @Bindable
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Bindable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Bindable
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Bindable
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Bindable
    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
