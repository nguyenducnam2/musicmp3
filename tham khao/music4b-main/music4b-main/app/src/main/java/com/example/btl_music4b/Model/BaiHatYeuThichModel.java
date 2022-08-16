package com.example.btl_music4b.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHatYeuThichModel implements Parcelable {

    @SerializedName("IdYeuThich")
    @Expose
    private int idYeuThich;

    @SerializedName("UserName")
    @Expose
    private String username;

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

    public BaiHatYeuThichModel(int idYeuThich, String username, int idBaiHat, String tenBaiHat, String hinhBaiHat, String tenCaSi, String linkBaiHat) {
        this.idYeuThich = idYeuThich;
        this.username = username;
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
        this.tenCaSi = tenCaSi;
        this.linkBaiHat = linkBaiHat;
    }

    public int getIdYeuThich() {
        return idYeuThich;
    }

    public void setIdYeuThich(int idYeuThich) {
        this.idYeuThich = idYeuThich;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    protected BaiHatYeuThichModel(Parcel in) {
        idYeuThich = in.readInt();
        username = in.readString();
        idBaiHat = in.readInt();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        tenCaSi = in.readString();
        linkBaiHat = in.readString();
    }

    public static final Creator<BaiHatYeuThichModel> CREATOR = new Creator<BaiHatYeuThichModel>() {
        @Override
        public BaiHatYeuThichModel createFromParcel(Parcel in) {
            return new BaiHatYeuThichModel(in);
        }

        @Override
        public BaiHatYeuThichModel[] newArray(int size) {
            return new BaiHatYeuThichModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idYeuThich);
        dest.writeString(username);
        dest.writeInt(idBaiHat);
        dest.writeString(tenBaiHat);
        dest.writeString(hinhBaiHat);
        dest.writeString(tenCaSi);
        dest.writeString(linkBaiHat);
    }
}
