package com.example.muizkapp.Service_API;

import com.example.muizkapp.Model.FeedbackRegister;
import com.example.muizkapp.Model.ResponseModel;
import com.example.muizkapp.Model.User;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Dataservice {


    @FormUrlEncoded
    @POST("login")
    Call<ResponseModel> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<FeedbackRegister> register(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("findByUsername")
    Call<List<User>> thongtinnguoidung(@Field("username") String username);


    @FormUrlEncoded
    @POST("resetPassword")
    Call<ResponseModel> updatepasswordnguoidung(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("validateEmail")
    Call<ResponseModel> checkEmail(@Field("email") String email);

}
