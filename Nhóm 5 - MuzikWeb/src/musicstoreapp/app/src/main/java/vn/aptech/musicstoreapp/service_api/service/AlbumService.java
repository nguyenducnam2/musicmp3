package vn.aptech.musicstoreapp.service_api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.aptech.musicstoreapp.entity.Album;
import vn.aptech.musicstoreapp.entity.Song;

public interface AlbumService {
    @GET("album")
    Call<List<Album>> findAll();

    @FormUrlEncoded
    @POST("album/getSongById")
    Call<List<Song>> getSongById(@Field("id") int id);
}
