package vn.aptech.musicstoreapp.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.aptech.musicstoreapp.model.Genre;

public interface GenreService {
    @GET("genre")
    Call<List<Genre>> findAll();
}
