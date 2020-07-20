package com.networkapplicationsdevelopment.finalproject.misc;

import java.io.Serializable;

import com.networkapplicationsdevelopment.finalproject.models.Product;

public class CartItem implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2144633907909505158L;
    private final Product product;
    private int quantity;

    public CartItem(Product product) {
        this.product = product;
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return this.quantity * this.product.getPrice();
    }
}
