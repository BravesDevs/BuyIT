package com.devangmp_8909274.buyit.Product.models;

public class Product {
    int id;
    String title;

    String brand;

    String price;
    String image;


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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // TODO: Add parameter for image when uncomment the image property in constructor.
    public Product(int id, String title, String brand, String price, String image) {
        this.id = id;
        this.title = title;
        this.brand = brand;
        this.price = price;
        this.image = image;
    }

    public Product() {
    }

}
