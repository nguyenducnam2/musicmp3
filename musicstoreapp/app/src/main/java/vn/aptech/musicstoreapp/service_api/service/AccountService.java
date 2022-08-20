package vn.aptech.musicstoreapp.service_api.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.aptech.musicstoreapp.entity.Account;

public interface AccountService {
    @GET("account")
    Call<List<Account>> findAll();

    @FormUrlEncoded
    @POST("loginAndroid")
    Call<Account> login(@Field("username")String username,@Field("password")String password);

    @FormUrlEncoded
    @POST("findByUsername")
    Call<List<Account>> findByUsername(@Field("username") String username);
}
