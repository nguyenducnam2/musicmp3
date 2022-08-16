package com.java.web_ecommerce_spring.models;

import com.java.web_ecommerce_spring.domain.Product;

public class ItemModel {
    Product product;
    int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemModel(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    public ItemModel() {
    }
}
