package com.java.web_ecommerce_spring.models;

import java.util.List;

public class OrderItem {
    List<ItemModel> itemModels;
    int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ItemModel> getItemModels() {
        return itemModels;
    }

    public void setItemModels(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }

    public OrderItem(List<ItemModel> itemModels,int total) {
        this.itemModels = itemModels;
        this.total  = total;
    }

    public OrderItem() {
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemModels=" + itemModels +
                '}';
    }
}
