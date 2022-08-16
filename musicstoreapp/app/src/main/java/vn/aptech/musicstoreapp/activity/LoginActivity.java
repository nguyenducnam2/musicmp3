package vn.aptech.musicstoreapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.fragment.Dialog_Forget_Password;

public class LoginActivity extends AppCompatActivity {



    androidx.appcompat.widget.Toolbar toolbarLogin;
    private SQLiteDatabase db;
    private TextInputLayout edPassword, edUsername;
    private Button btnLogin;
    private TextView tvForgetPassword;
    private boolean accept = false;
    private String username, password, name, email, image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
//        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                //noinspection deprecation
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (accept){
//                            GetDataUser(username);
                        }
                    }
                }, 3000);
            }
        });


        toolbarLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        Intent intent = new Intent(LoginActivity.this, Dialog_Forget_Password.class);
        startActivity(intent);
    }

//    private void GetDataUser(String username) {
//        Dataservice dataservice = APIService.getService();
//        Call<List<NguoiDungModel>> callback = dataservice.thongtinnguoidung(edUsername);
//        callback.enqueue(new Callback<List<NguoiDungModel>>() {
//            @Override
//            public void onResponse(Call<List<NguoiDungModel>> call, Response<List<NguoiDungModel>> response) {
//                ArrayList<NguoiDungModel> mangthongtinnguoidung = (ArrayList<NguoiDungModel>) response.body();
//                if (mangthongtinnguoidung.size() > 0){
//                    username = mangthongtinnguoidung.get(0).getUserName();
//                    password = mangthongtinnguoidung.get(0).getPassword();
//                    name = mangthongtinnguoidung.get(0).getNameuser();
//                    email = mangthongtinnguoidung.get(0).getEmail();
//                    image = mangthongtinnguoidung.get(0).getImage();
//                    InsertData(username, password, name, email, image);
//                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                }else {
//                    Toast.makeText(LoginActivity.this, "kết nối thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<NguoiDungModel>> call, Throwable t) {
//
//            }
//        });
//    }

    private void login() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        username = edUsername.getEditText().getText().toString().trim();
        password = edPassword.getEditText().getText().toString().trim();

//        Dataservice networkService = APIService.getService();
//        Call<ResponseModel> login = networkService.login(username, password);
//        login.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
//                ResponseModel responseBody = response.body();
//                if (responseBody != null) {
//                    if (responseBody.getSuccess().equals("1")) {
//                        accept = true;
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu sai !", Toast.LENGTH_LONG).show();
//                        progressDialog.dismiss();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
//                progressDialog.dismiss();
//            }
//        });
    }

    private void mapping() {
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);
        toolbarLogin = findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbarLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}