package com.networkapplicationsdevelopment.finalproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.google.gson.Gson;

@Entity
@Table(name = "products_categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private int product_id;

    @NotNull
    private int category_id;


    public ProductCategory() {
    }

    public ProductCategory(int product_id, int category_id) {
        this.product_id = product_id;
        this.category_id = category_id;
    }

    public int getProductId() {
        return this.product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public int getCategoryId() {
        return this.category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this).toString();
    }
}