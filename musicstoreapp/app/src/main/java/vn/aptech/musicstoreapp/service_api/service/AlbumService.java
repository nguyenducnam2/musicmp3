package vn.aptech.musicstoreapp.service_api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.aptech.musicstoreapp.entity.Album;

public interface AlbumService {
    @GET("album")
    Call<List<Album>> findAll();
}
