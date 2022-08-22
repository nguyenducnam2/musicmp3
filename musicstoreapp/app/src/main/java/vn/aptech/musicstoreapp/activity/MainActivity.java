package vn.aptech.musicstoreapp.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.entity.Account;
import vn.aptech.musicstoreapp.fragment.Dialog_Register;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.entity.Genre;
import vn.aptech.musicstoreapp.service_api.service.AccountService;
import vn.aptech.musicstoreapp.service_api.service.GenreService;

import vn.aptech.musicstoreapp.R;

public class MainActivity extends AppCompatActivity implements Dialog_Register.ExampleDialogListener  {

    private TextView tvDemo;
    private GenreService genreService;

    private SQLiteDatabase db;
    TextView edUsername;
    ImageView imgUser;
    LoginButton btnLoginFb;
    Button btnRegister, btnLogin;

    private long backPressTime;
    private Toast mToast;


    int flag = -1; // flag = 1 click free, flag = 2 click fb
    private String username, password;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        initData();
        getData();
        try {
            if (!(username.isEmpty() || password.isEmpty())) {
                login();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }


//        deleteData();
//        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
//        callbackManager = CallbackManager.Factory.create();


//        btnLoginFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                flag = 2;
//            }
//            @Override
//            public void onCancel() {
//            }
//            @Override
//            public void onError(FacebookException error) {
//            }
//        });

        callbackManager = CallbackManager.Factory.create();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                openDialog();

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


//        overridePendingTransition(R.anim.anim_intent_in_home, R.anim.anim_intent_out);

    }

    private void mapping() {
        tvDemo = findViewById(R.id.tvUsername);
        edUsername = findViewById(R.id.tvUsername);
        imgUser = findViewById(R.id.ivUser);
//        btnLoginFb = findViewById(R.id.btnLoginFb);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnGoToLogin);
        tvDemo.setText("MusicZik");
    }

    private void initData() {
        db = openOrCreateDatabase("Account.db", MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS account(id integer primary key autoincrement,Long account_id ,username text, password text,fullname text, role text, image text)";
        db.execSQL(sql);
    }

    private void getData() {
        String sql = "SELECT * FROM account";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToLast();
        if (!cursor.isAfterLast()) {
            username = cursor.getString(1);
            password = cursor.getString(2);
            Log.e(TAG,"username:"+username+"-password"+password);
        }
    }

    private void login() {
        AccountService networkService = ApiUtil.getAccountService();
        Call<Account> login = networkService.login(username, password);
        login.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                Account responseBody = response.body();
                if (responseBody != null) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Account> call, @NonNull Throwable t) {
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        if (backPressTime + 2000 > System.currentTimeMillis()){
//            mToast.cancel();
//
//            Intent intent = new Intent(getApplicationContext(), KhoiDongActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.putExtra("EXIT", true);
//            startActivity(intent);
//            finish();
//            System.exit(0);
//
//        }else {
//            mToast = Toast.makeText(MainActivity.this, "Ấn lần nữa để thoát", Toast.LENGTH_SHORT);
//            mToast.show();
//        }
//        backPressTime = System.currentTimeMillis();
//    }


    private void register(String username,String password) {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AccountService dataService = ApiUtil.getAccountService();
        Call<ResponseBody> registerCall = dataService.registerAndroid( username,password);
        registerCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    Toast.makeText(MainActivity.this, "Register Success !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
//                else {
//                            Toast.makeText(MainActivity.this, "Đăng nhập bằng facebook !", Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                            startActivity(intent);
//                    }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });

    }




    private void openDialog() {
        Dialog_Register exampleDialog = new Dialog_Register();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    public void apply(String username1, String password1){
//        HashMap<String, String> params = new HashMap<>();
        username = username1;
        password = password1;
//        params.put("UserName", username);
//        params.put("Password", password);
        register(username1,password1);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        accessTokenTracker.stopTracking();
//    }

//    private void InsertData(String tk, String mk, String ten, String email, String url) {
//        String sql = "INSERT INTO tbNguoiDung(TaiKhoan, MatKhau, Ten, Email, ImageURL) VALUES('"+tk+"','"+mk+"','"+ten+"','"+email+"','"+url+"')";
//        db.execSQL(sql);
//    }
//    private void deleteData(){
//        String sql = "DELETE FROM tbNguoiDung";
//        db.execSQL(sql);
//    }
}