package vn.aptech.musicstoreapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

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
}
