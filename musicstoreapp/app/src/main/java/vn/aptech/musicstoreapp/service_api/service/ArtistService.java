package vn.aptech.musicstoreapp.service_api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.aptech.musicstoreapp.entity.Artist;

public interface ArtistService {
    @GET("artist")
    Call<List<Artist>> findAll();
}
