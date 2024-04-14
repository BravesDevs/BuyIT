package com.devangmp_8909274.buyit.Cart.models;

import com.devangmp_8909274.buyit.Product.models.Product;

public class CartProduct extends Product {
    private int quantity;

    public CartProduct() {
    }

    public CartProduct(Product product, int quantity) {
        super(product.getTitle(), product.getPrice(), product.getImage());
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
