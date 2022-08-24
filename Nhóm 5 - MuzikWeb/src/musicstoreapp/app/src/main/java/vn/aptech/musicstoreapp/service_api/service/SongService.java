package vn.aptech.musicstoreapp.service_api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.aptech.musicstoreapp.entity.Song;

public interface SongService {
    @GET("song")
    Call<List<Song>> findAll();

    @FormUrlEncoded
    @POST("song/searchByName")
    Call<List<Song>> getSongByName(@Field("name") String name);
}
