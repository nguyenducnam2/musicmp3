package com.example.btl_music4b.Service_API;

import com.example.btl_music4b.Model.BaiHatModel;
import com.example.btl_music4b.Model.BaiHatThuVienPlayListModel;
import com.example.btl_music4b.Model.BaiHatYeuThichModel;
import com.example.btl_music4b.Model.BangXepHangModel;
import com.example.btl_music4b.Model.ChuDeModel;
import com.example.btl_music4b.Model.ResponseModel;
import com.example.btl_music4b.Model.NgheSiModel;
import com.example.btl_music4b.Model.NguoiDungModel;
import com.example.btl_music4b.Model.PhoBienModel;
import com.example.btl_music4b.Model.PlaylistModel;
import com.example.btl_music4b.Model.PhanHoiDangKyModel;
import com.example.btl_music4b.Model.ThinhHanhModel;
import com.example.btl_music4b.Model.ThuVienPlayListModel;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("playlistcurrentday.php")
    Call<List<PlaylistModel>> GetPlaylistCurrentDay();

    @GET("nghesicurrent.php")
    Call<List<NgheSiModel>> GetNgheSiCurrent();

    @GET("thinhhanhcurrent.php")
    Call<List<ThinhHanhModel>> GetThinhHanhCurrent();

    @GET("phobiencurrent.php")
    Call<List<PhoBienModel>> GetPhoBienCurrent();

    @GET("chudecurrent.php")
    Call<List<ChuDeModel>> GetChuDeCurrent();

    @GET("bangxephangcurrent.php")
    Call<List<BangXepHangModel>> GetBangXepHangCurrent();

    @FormUrlEncoded
    @POST("thuvienplaylist.php")
    Call<List<ThuVienPlayListModel>> GetBangThuVienPlayList(@Field("username") String username);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatplaylist(@Field("idplaylist") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatnghesi(@Field("idnghesi") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatthinhhanh(@Field("idthinhhanh") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatphobien(@Field("idphobien") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatchude(@Field("idchude") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatModel>> GetDanhsachbaihatbangxephang(@Field("idbangxephang") String id);

    @FormUrlEncoded
    @POST("danhsachbaihatthuvienplaylist.php")
    Call<List<BaiHatThuVienPlayListModel>> GetDanhsachbaihatthuvienplaylist(@Field("idthuvienplaylist") String id);

    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<BaiHatModel>> GetTimKiembaihat(@Field("tukhoa") String tukhoa);


    @FormUrlEncoded
    @POST("insertthuvienplaylist.php")
    Call<PhanHoiDangKyModel> insertthuvien(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("insertnhacthuvienplaylist.php")
    Call<ResponseModel> insertnhacthuvien(@Field("idthuvien") int idthuvien, @Field("idbaihat") int idbaihat,
                                          @Field("tenbaihat") String tenbaihat, @Field("tencasi") String tencasi,
                                          @Field("hinhbaihat") String hinhbaihat, @Field("linkbaihat") String linkbaihat);

    @FormUrlEncoded
    @POST("deletethuvienplaylist.php")
    Call<ResponseModel> deletethuvien(@Field("idthuvien") int idthuven);

    @FormUrlEncoded
    @POST("deletebaihatthuvien.php")
    Call<ResponseModel> deletemotbaihatthuvien(@Field("idbaihatthuvien") int idbaihatthuvien);

    @FormUrlEncoded
    @POST("deletebaihatthuvien.php")
    Call<ResponseModel> deletenhieubaihatthuvien(@Field("idthuvien") int idthuvien);

    @FormUrlEncoded
    @POST("insertyeuthich.php")
    Call<ResponseModel> insertyeuthich(@Field("username") String username, @Field("idbaihat") int idbaihat,
                                       @Field("tenbaihat") String tenbaihat, @Field("tencasi") String tencasi,
                                       @Field("hinhbaihat") String hinhbaihat, @Field("linkbaihat") String linkbaihat);

    @FormUrlEncoded
    @POST("deleteyeuthich.php")
    Call<ResponseModel> deleteyeuthich(@Field("username") String username, @Field("idbaihat") int idbaihat);

    @FormUrlEncoded
    @POST("checkyeuthich.php")
    Call<ResponseModel> checkyeuthich(@Field("username") String username, @Field("idbaihat") int idbaihat);

    @FormUrlEncoded
    @POST("danhsachyeuthich.php")
    Call<List<BaiHatYeuThichModel>> danhsachyeuthich(@Field("username") String username);

    @FormUrlEncoded
    @POST("updatehinhthuvien.php")
    Call<ResponseModel> updatehinhthuvien(@Field("idthuvien") int idthuvien, @Field("hinhthuvien") String hinhthuvien);

    @FormUrlEncoded
    @POST("dangky.php")
    Call<PhanHoiDangKyModel> register(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("dangnhap.php")
    Call<ResponseModel> login(@Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

    @FormUrlEncoded
    @POST("getthongtinnguoidung.php")
    Call<List<NguoiDungModel>> thongtinnguoidung(@Field("username") String username);

    @FormUrlEncoded
    @POST("updatepasswordnguoidung.php")
    Call<ResponseModel> updatepasswordnguoidung(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("checkemail.php")
    Call<ResponseModel> checkemail(@Field("email") String email);

    @FormUrlEncoded
    @POST("checkusername.php")
    Call<ResponseModel> checkusername(@Field("username") String username);

    @FormUrlEncoded
    @POST("uploadimage.php")
    Call<ResponseModel> insertimage(@FieldMap HashMap<String, String> params);

}
