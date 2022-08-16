package com.example.btl_music4b.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NguoiDungModel implements Serializable {
    @SerializedName("UserName")
    @Expose
    private String userName;

    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("Name")
    @Expose
    private String nameUser;

    @SerializedName("Email")
    @Expose
    private String email;

    @SerializedName("Image")
    @Expose
    private String image;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameuser() {
        return nameUser;
    }

    public void setNameuser(String nameuser) {
        this.nameUser = nameuser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
