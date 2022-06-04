package com.example.bookmall.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Book extends BaseObservable {
    private int id;
    private String name;
    private String coverPic;
    private float price;
    private String author;
    private String ISBN;
    private String description;
    private String category;


    public Book(int id,String name,String coverPic,float price,String author,String ISBN,String description,String category){
        this.id = id;
        this.name = name;
        this.coverPic = coverPic;
        this.price = price;
        this.author = author;
        this.ISBN = ISBN;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
