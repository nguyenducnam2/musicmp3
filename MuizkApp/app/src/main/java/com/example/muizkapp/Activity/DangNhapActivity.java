package com.example.muizkapp.Activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.muizkapp.Fragment.Dialog_Forget_Password;
import com.example.muizkapp.Model.ResponseModel;
import com.example.muizkapp.Model.User;
import com.example.muizkapp.R;
import com.example.muizkapp.Service_API.APIService;
import com.example.muizkapp.Service_API.Dataservice;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbardangnhap;
    private SQLiteDatabase db;
    private TextInputLayout matkhau, taikhoan;
    private Button dangnhap;
    private TextView qmk;
    private boolean accept = false;
    private String username, password, name, email, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        AnhXa();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        db = openOrCreateDatabase("NguoiDung.db", MODE_PRIVATE, null);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                //noinspection deprecation
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (accept){
                            GetDataUser(username);
                        }
                    }
                }, 3000);
            }
        });

        toolbardangnhap.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        qmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }
    private void openDialog() {
        Intent intent = new Intent(DangNhapActivity.this, Dialog_Forget_Password.class);
        startActivity(intent);
    }
    private void login() {

        final ProgressDialog progressDialog = new ProgressDialog(DangNhapActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        username = taikhoan.getEditText().getText().toString().trim();
        password = matkhau.getEditText().getText().toString().trim();

        Dataservice networkService = APIService.getService();
        Call<ResponseModel> login = networkService.login(username, password);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        accept = true;
                    } else {
                        Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mật khẩu sai !", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
    private void GetDataUser(String taikhoan) {
        Dataservice dataservice = APIService.getService();
        Call<List<User>> callback = dataservice.thongtinnguoidung(taikhoan);
        callback.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                ArrayList<User> mangthongtinnguoidung = (ArrayList<User>) response.body();
                if (mangthongtinnguoidung.size() > 0){
                    username = mangthongtinnguoidung.get(0).getEmail();
                    password = mangthongtinnguoidung.get(0).getPassword();
                    name = mangthongtinnguoidung.get(0).getEmail();
                    email = mangthongtinnguoidung.get(0).getEmail();
                    image = mangthongtinnguoidung.get(0).getImage();
                    InsertData(username, password, name, email, image);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }else {
                    Toast.makeText(DangNhapActivity.this, "kết nối thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    private void AnhXa(){
        taikhoan = findViewById(R.id.edttkdn);
        matkhau = findViewById(R.id.edtmkdn);
        dangnhap = findViewById(R.id.btndn);
        qmk = findViewById(R.id.textViewquenmatkhau);
        toolbardangnhap = findViewById(R.id.toolbardangnhap);
        setSupportActionBar(toolbardangnhap);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void InsertData(String tk, String mk, String ten,String email, String url) {
        String sql = "INSERT INTO tbNguoiDung(TaiKhoan, MatKhau, Ten, Email, ImageURL) VALUES('"+tk+"','"+mk+"','"+ten+"','"+email+"','"+url+"')";
        db.execSQL(sql);
    }

}