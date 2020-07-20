package com.networkapplicationsdevelopment.finalproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.gson.Gson;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private int user_id;

    @NotNull
    private int product_id;

    @NotNull
    private int quantity;

    @NotNull
    private int price;

    @NotNull
    private int timestamp;

    @NotNull
    private String session_id;

    public Purchase() {
    }

    public Purchase(int id, int user_id, int product_id, int quantity, int price, int timestamp, String session_id) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
        this.session_id = session_id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return this.user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getProductId() {
        return this.product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getSessionId() {
        return this.session_id;
    }

    public void setSessionId(String session_id) {
        this.session_id = session_id;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this).toString();
    }

}