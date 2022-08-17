package com.java.web_ecommerce_spring.models;

public class AddDiscountCode
{
     int status ;
     int total;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AddDiscountCode() {
    }

    public AddDiscountCode(int status, int total) {
        this.status = status;
        this.total = total;
    }

    @Override
    public String toString() {
        return "AddDiscountCode{" +
                "status=" + status +
                ", total=" + total +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
