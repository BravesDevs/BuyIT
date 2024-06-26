package com.devangmp_8909274.buyit.Product.models;

public class Product {
    int id;
    String title;
    String brand;
    int price;
    String image;

    public Product(String title, int price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product(int id, String title, String brand, int price, String image) {
        this.id = id;
        this.title = title;
        this.brand = brand;
        this.price = price;
        this.image = image;
    }

    public Product() {
    }

}
