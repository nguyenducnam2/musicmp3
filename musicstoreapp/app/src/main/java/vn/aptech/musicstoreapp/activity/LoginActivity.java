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
import vn.aptech.musicstoreapp.entity.Account;
import vn.aptech.musicstoreapp.fragment.Dialog_Forget_Password;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.AccountService;

public class LoginActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbarLogin;
    private SQLiteDatabase db;
    private TextInputLayout edPassword, edUsername;
    private Button btnLogin;
    private TextView tvForgetPassword;
    private boolean accept = false;
    private String username, password, fullname, role, image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
//        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        db = openOrCreateDatabase("Account.db", MODE_PRIVATE, null);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                //noinspection deprecation
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (accept) {
                            GetDataUser();
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

    private void InsertData(String tk, String mk, String ten,String role, String url) {
        String sql = "INSERT INTO account(username, password, fullname, role, image) VALUES('"+tk+"','"+mk+"','"+ten+"','"+role+"','"+url+"')";
        db.execSQL(sql);
    }

    private void GetDataUser() {
        AccountService dataservice = ApiUtil.getAccountService();
        Call<Account> callback = dataservice.login(username, password);
        callback.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Account account = (Account) response.body();
                if (account != null) {
                    username = account.getUsername();
                    password = account.getPassword();
                    fullname = account.getFullname();
                    role = account.getRole();
                    image = account.getImageUrl();
                    InsertData(username, password, fullname, role, image);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Connect Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }

    private void login() {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        username = edUsername.getEditText().getText().toString().trim();
        password = edPassword.getEditText().getText().toString().trim();

        AccountService networkService = ApiUtil.getAccountService();
        Call<Account> login = networkService.login(username, password);
        login.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                Account responseBody = response.body();
                if (responseBody != null) {
                    accept = true;
                } else {
                    Toast.makeText(LoginActivity.this, "Login Fail !", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<Account> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
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