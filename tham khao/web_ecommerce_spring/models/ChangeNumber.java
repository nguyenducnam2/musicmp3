package com.java.web_ecommerce_spring.models;

public class ChangeNumber {
    String giaCu;
    String giaMoi;
    String total;
    String tamTinh;

    public String getTamTinh() {
        return tamTinh;
    }

    public void setTamTinh(String tamTinh) {
        this.tamTinh = tamTinh;
    }

    public String getGiaCu() {
        return giaCu;
    }

    public void setGiaCu(String giaCu) {
        this.giaCu = giaCu;
    }

    public String getGiaMoi() {
        return giaMoi;
    }

    public void setGiaMoi(String giaMoi) {
        this.giaMoi = giaMoi;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    public ChangeNumber() {

    }

    public ChangeNumber(String giaCu, String giaMoi, String total, String tamTinh) {
        this.giaCu = giaCu;
        this.giaMoi = giaMoi;
        this.total = total;
        this.tamTinh = tamTinh;
    }
}
