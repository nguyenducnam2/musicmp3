package vn.aptech.musicstoreapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("enabled")
    @Expose
    private Boolean enabled = false;
    @SerializedName("image")
    @Expose
    private String image;

    public Account() {
    }

    protected Account(Parcel in) {
        id = in.readInt();
        username = in.readString();
        password = in.readString();
        fullname = in.readString();
        role = in.readString();
        byte tmpEnabled = in.readByte();
        enabled = tmpEnabled == 0 ? null : tmpEnabled == 1;
        image = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getImageUrl() {
        return image;
    }

    public void setImageUrl(String imageUrl) {
        this.image = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(fullname);
        parcel.writeString(role);
        parcel.writeByte((byte) (enabled == null ? 0 : enabled ? 1 : 2));
        parcel.writeString(image);
    }
}
