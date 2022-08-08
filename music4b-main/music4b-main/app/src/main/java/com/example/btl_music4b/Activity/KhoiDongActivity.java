package com.example.btl_music4b.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_music4b.Model.ResponseModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KhoiDongActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    View view;
    Animation animation;

    private String taikhoan ="", matkhau="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)){
            finish();
        }else {
            view = findViewById(R.id.logomain);
            initData();
            getData();
            overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
            animation = AnimationUtils.loadAnimation(KhoiDongActivity.this, R.anim.anim_intent_in_main);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.VISIBLE);
                    view.startAnimation(animation);
                }
            }, 2500);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (taikhoan.isEmpty() || matkhau.isEmpty()){
                        startActivity(new Intent(getApplicationContext(), DangKyActivity.class));
                    }else {
                        login();
                    }
                }
            }, 5000);
        }
    }

    private void login() {

        Dataservice networkService = APIService.getService();
        Call<ResponseModel> login = networkService.login(taikhoan, matkhau);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        startActivity(new Intent(getApplicationContext(), DangKyActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
            }
        });
    }

    private void initData() {
        db = openOrCreateDatabase("NguoiDung.db", MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS tbNguoiDung(Id integer primary key autoincrement,TaiKhoan text, MatKhau text, Ten text, Email text, ImageURL text)";
        db.execSQL(sql);
    }
    private void getData() {
        String sql = "SELECT * FROM tbNguoiDung";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            taikhoan = cursor.getString(1);
            matkhau = cursor.getString(2);
        }
    }

}