package vn.aptech.musicstoreapp.service_api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.aptech.musicstoreapp.entity.Artist;
import vn.aptech.musicstoreapp.entity.Song;

public interface ArtistService {
    @GET("artist")
    Call<List<Artist>> findAll();

    @FormUrlEncoded
    @POST("artist/getSongById")
    Call<List<Song>> getSongById(@Field("id")int id);
}
