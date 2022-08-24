package vn.aptech.musicstoreapp.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.entity.ResponseModel;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.AccountService;

public class StartActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    View view;
    Animation animation;

    private String username ="", password="";

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
//            overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
//            animation = AnimationUtils.loadAnimation(StartActivity.this, R.anim.anim_intent_in_main);

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    view.setVisibility(View.VISIBLE);
//                    view.startAnimation(animation);
//                }
//            }, 2500);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences sharedPreferences = getSharedPreferences("application", Context.MODE_PRIVATE);
                    username = sharedPreferences.getString("username","");
                    if (username.isEmpty()){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                }
            }, 100);
        }
    }

//    private void login() {
//        AccountService networkService = ApiUtil.getAccountService();
//        Call<ResponseModel> login = networkService.login(username, password);
//        login.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
//                ResponseModel responseBody = response.body();
//                if (responseBody != null) {
//                    if(responseBody.getSuccess().equals("success")){
//                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                    } else {
//                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
//            }
//        });
//    }

    private void initData() {
        db = openOrCreateDatabase("Account.db", MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS account(id integer primary key autoincrement , username text, password text,fullname text, role text, image text)";
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

}