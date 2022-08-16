package com.example.btl_music4b.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThuVienPlayListModel implements Serializable {
    @SerializedName("IDThuVienPlayList")
    @Expose
    private int idThuVienPlayList;

    @SerializedName("TenThuVienPlayList")
    @Expose
    private String tenThuVienPlayList;

    @SerializedName("HinhThuVienPlayList")
    @Expose
    private String hinhThuVienPlaylist;

    @SerializedName("UserName")
    @Expose
    private String userName;

    public int getIDThuVienPlayList() {
        return idThuVienPlayList;
    }

    public void setIDThuVienPlayList(int idThuVienPlayList) {
        this.idThuVienPlayList = idThuVienPlayList;
    }

    public String getTenThuVienPlayList() {
        return tenThuVienPlayList;
    }

    public void setTenThuVienPlayList(String tenThuVienPlayList) {
        this.tenThuVienPlayList = tenThuVienPlayList;
    }

    public String getHinhThuVienPlaylist() {
        return hinhThuVienPlaylist;
    }

    public void setHinhThuVienPlaylist(String hinhThuVienPlaylist) {
        this.hinhThuVienPlaylist = hinhThuVienPlaylist;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
