package vn.aptech.musicstoreapp.service_api.service;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.aptech.musicstoreapp.entity.Account;
import vn.aptech.musicstoreapp.entity.ResponseModel;

public interface AccountService {
    @GET("account")
    Call<List<Account>> findAll();

    @FormUrlEncoded
    @POST("loginAndroid")
    Call<Account> login(@Field("username")String username,@Field("password")String password);

//    @FormUrlEncoded
//    @POST("findByUsername")
//    Call<List<Account>> findByUsername(@Field("username") String username);

    @FormUrlEncoded
    @POST("findByUsername")
    Call<Account> findByUsername(@Field("username") String username);

    @FormUrlEncoded
    @POST("resetPasswordAndroid")
    Call<ResponseBody> resetPasswordAndroid(@Field("username")String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("checkUsernameRegisterAndroid")
    Call<ResponseModel> checkUsername(@Field("username")String username);

    @FormUrlEncoded
    @POST("registerAndroid")
    Call<ResponseBody> registerAndroid(@FieldMap HashMap<String, String> params);
}
