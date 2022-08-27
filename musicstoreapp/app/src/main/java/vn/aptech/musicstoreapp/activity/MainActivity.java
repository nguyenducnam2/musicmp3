package vn.aptech.musicstoreapp.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import vn.aptech.musicstoreapp.entity.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.entity.Account;
import vn.aptech.musicstoreapp.entity.ResponseModel;
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
    Button btnRegister, btnLogin,btnLogout;
    HomeActivity hm;
    private long backPressTime;
    private Toast mToast;


    int flag = -1; // flag = 1 click free, flag = 2 click fb
//    private String username, password;

    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
//        username = sharedPreferences.getString("username","");
////        password = sharedPreferences.getString("password","");
//        if (username.equalsIgnoreCase("")){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        }
//        else {
//            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//        }

        mapping();
        callbackManager = CallbackManager.Factory.create();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                openDialog();


            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        /*btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent it = new Intent(MainActivity.this, MainActivity.class);
                finishAffinity();
                startActivity(it);
            }
        });*/


//        overridePendingTransition(R.anim.anim_intent_in_home, R.anim.anim_intent_out);

    }



    private void mapping() {
        tvDemo = findViewById(R.id.tv1);
        imgUser = findViewById(R.id.ivUser);
//        btnLoginFb = findViewById(R.id.btnLoginFb);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnGoToLogin);
        //btnLogout = findViewById(R.id.btnLogout);
        tvDemo.setText("MusicZik");
    }





    @Override
    public void onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()){
            mToast.cancel();

            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            finish();
            System.exit(0);

        }else {
            mToast = Toast.makeText(MainActivity.this, "Press again to exit", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressTime = System.currentTimeMillis();
    }


    private void register(String username,String password) {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AccountService dataService = ApiUtil.getAccountService();
        Call<ResponseModel> registerCall = dataService.registerAndroid(username,password);
        registerCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if(responseBody.getSuccess().equals("success")){
                        Toast.makeText(MainActivity.this, "Register Success !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });

    }


//    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//            LoginManager.getInstance().logOut();
//        }
//    };

    private void openDialog() {
        Dialog_Register exampleDialog = new Dialog_Register();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    public void apply(String username, String password){
//        HashMap<String, String> params = new HashMap<>();
//        username = username1;
//        password = password1;
//        params.put("UserName", username);
//        params.put("Password", password);
        register(username,password);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        accessTokenTracker.stopTracking();
//    }

//    private void insertData(String username, String password) {
//        String sql = "INSERT INTO account(id integer primary key autoincrement,username , password ) VALUES('"+username+"','"+password+"')";
//        db.execSQL(sql);
//    }
}