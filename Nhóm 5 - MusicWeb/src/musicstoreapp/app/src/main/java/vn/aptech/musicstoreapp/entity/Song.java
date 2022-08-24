package vn.aptech.musicstoreapp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;

public class Song implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("media")
    @Expose
    private String media;
    @SerializedName("lyric")
    @Expose
    private String lyric;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("albumId")
    @Expose
    private Integer albumId;
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    @SerializedName("genreId")
    @Expose
    private Integer genreId;
    @SerializedName("accountId")
    @Expose
    private Long accountId;

    @SerializedName("album")
    @Expose
    private Album album;
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("genre")
    @Expose
    private Genre genre;
    @SerializedName("account")
    @Expose
    private Account account;

    public Song() {
    }

    public Song(Integer id, String name, String media, String lyric, Integer view, String video, Integer price, String image, Integer albumId, Integer artistId, Integer genreId, Long accountId, Album album, Artist artist, Genre genre, Account account) {
        this.id = id;
        this.name = name;
        this.media = media;
        this.lyric = lyric;
        this.view = view;
        this.video = video;
        this.price = price;
        this.image = image;
        this.albumId = albumId;
        this.artistId = artistId;
        this.genreId = genreId;
        this.accountId = accountId;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
        this.account = account;
    }

    protected Song(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        media = in.readString();
        lyric = in.readString();
        if (in.readByte() == 0) {
            view = null;
        } else {
            view = in.readInt();
        }
        video = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
        image = in.readString();
        if (in.readByte() == 0) {
            albumId = null;
        } else {
            albumId = in.readInt();
        }
        if (in.readByte() == 0) {
            artistId = null;
        } else {
            artistId = in.readInt();
        }
        if (in.readByte() == 0) {
            genreId = null;
        } else {
            genreId = in.readInt();
        }
        if (in.readByte() == 0) {
            accountId = null;
        } else {
            accountId = in.readLong();
        }
        genre=in.readParcelable(Genre.class.getClassLoader());
        artist=in.readParcelable(Artist.class.getClassLoader());
        album=in.readParcelable(Album.class.getClassLoader());
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(media);
        dest.writeString(lyric);
        if (view == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(view);
        }
        dest.writeString(video);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(price);
        }
        dest.writeString(image);
        if (albumId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(albumId);
        }
        if (artistId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(artistId);
        }
        if (genreId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(genreId);
        }
        if (accountId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(accountId);
        }
        dest.writeParcelable(genre,flags);
        dest.writeParcelable(artist,flags);
        dest.writeParcelable(album,flags);
    }
}
