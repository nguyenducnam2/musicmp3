package com.example.btl_music4b.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaiHatModel implements Parcelable {
    @SerializedName("IdBaiHat")
    @Expose
    private int idBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;
    @SerializedName("TenCaSi")
    @Expose
    private String tenCaSi;
    @SerializedName("LinkBaiHat")
    @Expose
    private String linkBaiHat;

    public BaiHatModel(int idBaiHat, String tenBaiHat, String hinhBaiHat, String tenCaSi, String linkBaiHat) {
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
        this.tenCaSi = tenCaSi;
        this.linkBaiHat = linkBaiHat;
    }


    protected BaiHatModel(Parcel in) {
        idBaiHat = in.readInt();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        tenCaSi = in.readString();
        linkBaiHat = in.readString();
    }

    public static final Creator<BaiHatModel> CREATOR = new Creator<BaiHatModel>() {
        @Override
        public BaiHatModel createFromParcel(Parcel in) {
            return new BaiHatModel(in);
        }

        @Override
        public BaiHatModel[] newArray(int size) {
            return new BaiHatModel[size];
        }
    };

    public int getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(int idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }

    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }

    public String getLinkBaiHat() {
        return linkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        this.linkBaiHat = linkBaiHat;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idBaiHat);
        parcel.writeString(tenBaiHat);
        parcel.writeString(hinhBaiHat);
        parcel.writeString(tenCaSi);
        parcel.writeString(linkBaiHat);
    }

}
