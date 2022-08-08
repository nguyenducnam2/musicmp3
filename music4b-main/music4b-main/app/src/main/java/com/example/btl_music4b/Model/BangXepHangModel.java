package com.example.btl_music4b.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BangXepHangModel implements Serializable {
    @SerializedName("IdBangXepHang")
    @Expose
    private String idBangXepHang;
    @SerializedName("TenBangXepHang")
    @Expose
    private String tenBangXepHang;
    @SerializedName("HinhBangXepHang")
    @Expose
    private String hinhBangXepHang;

    public String getIdBangXepHang() {
        return idBangXepHang;
    }

    public void setIdBangXepHang(String idBangXepHang) {
        this.idBangXepHang = idBangXepHang;
    }

    public String getTenBangXepHang() {
        return tenBangXepHang;
    }

    public void setTenBangXepHang(String tenBangXepHang) {
        this.tenBangXepHang = tenBangXepHang;
    }

    public String getHinhBangXepHang() {
        return hinhBangXepHang;
    }

    public void setHinhBangXepHang(String hinhBangXepHang) {
        this.hinhBangXepHang = hinhBangXepHang;
    }
}
