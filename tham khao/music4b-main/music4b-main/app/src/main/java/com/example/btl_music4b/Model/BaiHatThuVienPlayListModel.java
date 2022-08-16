package com.example.btl_music4b.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHatThuVienPlayListModel implements Parcelable {

    @SerializedName("IdBaiHatThuVienPlayList")
    @Expose
    private int idBaiHatThuVienPlayList;

    @SerializedName("IdThuVienPlayList")
    @Expose
    private int idThuVienPlayList;

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

    public BaiHatThuVienPlayListModel(int idBaiHatThuVienPlayList, int idThuVienPlayList, int idBaiHat,
                                      String tenBaiHat, String hinhBaiHat, String tenCaSi, String linkBaiHat) {
        this.idBaiHatThuVienPlayList = idBaiHatThuVienPlayList;
        this.idThuVienPlayList = idThuVienPlayList;
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
        this.tenCaSi = tenCaSi;
        this.linkBaiHat = linkBaiHat;
    }


    protected BaiHatThuVienPlayListModel(Parcel in) {
        idBaiHatThuVienPlayList = in.readInt();
        idThuVienPlayList = in.readInt();
        idBaiHat = in.readInt();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        tenCaSi = in.readString();
        linkBaiHat = in.readString();
    }

    public int getIdBaiHatThuVienPlayList() {
        return idBaiHatThuVienPlayList;
    }

    public void setIdBaiHatThuVienPlayList(int idBaiHatThuVienPlayList) {
        this.idBaiHatThuVienPlayList = idBaiHatThuVienPlayList;
    }

    public int getIdThuVienPlayList() {
        return idThuVienPlayList;
    }

    public void setIdThuVienPlayList(int idThuVienPlayList) {
        this.idThuVienPlayList = idThuVienPlayList;
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

    public static final Creator<BaiHatThuVienPlayListModel> CREATOR = new Creator<BaiHatThuVienPlayListModel>() {
        @Override
        public BaiHatThuVienPlayListModel createFromParcel(Parcel in) {
            return new BaiHatThuVienPlayListModel(in);
        }

        @Override
        public BaiHatThuVienPlayListModel[] newArray(int size) {
            return new BaiHatThuVienPlayListModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idBaiHatThuVienPlayList);
        dest.writeInt(idThuVienPlayList);
        dest.writeInt(idBaiHat);
        dest.writeString(tenBaiHat);
        dest.writeString(hinhBaiHat);
        dest.writeString(tenCaSi);
        dest.writeString(linkBaiHat);
    }
}
