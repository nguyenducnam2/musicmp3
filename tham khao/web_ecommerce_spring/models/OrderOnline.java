package com.java.web_ecommerce_spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderOnline {

    private double price;
    private String currency;
    private int orderId;
    private String method;
    private String intent;
    private String description;

}