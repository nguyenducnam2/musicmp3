package com.example.btl_music4b.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_music4b.Fragment.Dialog_Dangky_Free;
import com.example.btl_music4b.Model.PhanHoiDangKyModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity implements Dialog_Dangky_Free.ExampleDialogListener {
    private SQLiteDatabase db;
    TextView nameUser;
    ImageView imgUser;
    LoginButton lgbtn;
    Button btndkfree, btndnfree;

    private long backPressTime;
    private Toast mToast;

    int flag = -1; // flag = 1 click free, flag = 2 click fb
    private String username, password, name, email="", imageurl, test = "";

    private CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        AnhXa();
        DeleteData();
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        callbackManager = CallbackManager.Factory.create();
        lgbtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                flag = 2;
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
        btndkfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                openDialog();
            }
        });
        btndnfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });
        overridePendingTransition(R.anim.anim_intent_in_home, R.anim.anim_intent_out);
    }

    @Override
    public void onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()){
            mToast.cancel();

            Intent intent = new Intent(getApplicationContext(), KhoiDongActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            finish();
            System.exit(0);

        }else {
            mToast = Toast.makeText(DangKyActivity.this, "Ấn lần nữa để thoát", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backPressTime = System.currentTimeMillis();
    }

    private void AnhXa() {
        nameUser = findViewById(R.id.textView6);
        imgUser = findViewById(R.id.imageView);
        lgbtn = findViewById(R.id.login_button);
        btndkfree = findViewById(R.id.loginfree);
        btndnfree = findViewById(R.id.btndangnhap);
        db = openOrCreateDatabase("NguoiDung.db", MODE_PRIVATE, null);
    }

    private void register(HashMap<String, String> params) {

        final ProgressDialog progressDialog = new ProgressDialog(DangKyActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Dataservice networkService = APIService.getService();
        Call<PhanHoiDangKyModel> registerCall = networkService.register(params);
        registerCall.enqueue(new Callback<PhanHoiDangKyModel>() {
            @Override
            public void onResponse(@NonNull Call<PhanHoiDangKyModel> call, @NonNull Response<PhanHoiDangKyModel> response) {
                PhanHoiDangKyModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        if (flag == 1){
                            InsertData(username, password, username, email, imageurl);
                        }else if (flag == 2){
                            InsertData(username, password, name, email, imageurl);
                        }
                        Intent intent = new Intent(DangKyActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else if (responseBody.getSuccess().equals("0")){
                        if (flag == 1){ //dang ky free
                            Toast.makeText(DangKyActivity.this, "Tài khoản đã được đăng ký !", Toast.LENGTH_LONG).show();
                        }else if (flag == 2){ // dang nhap bang facebook
                            Toast.makeText(DangKyActivity.this, "Đăng nhập bằng facebook !", Toast.LENGTH_LONG).show();
                            InsertData(username, password, name, email, imageurl);
                            Intent intent = new Intent(DangKyActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(@NonNull Call<PhanHoiDangKyModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (flag == 2){
            GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            try {
                                username = object.getString("id");
                                password = "LhKbEpmsWkl56J00r34vcnmTg";
                                name = object.getString("name");
                                email = "-";
                                imageurl = "https://graph.facebook.com/"+username+"/picture?style=large";

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            HashMap<String, String> params = new HashMap<>();
                            params.put("UserName", username);
                            params.put("Password", password);
                            params.put("Name", name);
                            params.put("Email", email);
                            params.put("Image", imageurl);
                            register(params);
                        }
                    });

            Bundle bundle = new Bundle();
            bundle.putString("fields", "name, id");

            graphRequest.setParameters(bundle);
            graphRequest.executeAsync();
        }
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            LoginManager.getInstance().logOut();
        }
    };

    private void openDialog() {
        Dialog_Dangky_Free exampleDialog = new Dialog_Dangky_Free();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    public void apply(String taikhoan, String matkhau, String emailz){
        HashMap<String, String> params = new HashMap<>();
        username = taikhoan;
        password = matkhau;
        name = taikhoan;
        email = emailz;
        imageurl = "https://music4b.000webhostapp.com/HinhAnh/spotify_64px.png";
        params.put("UserName", taikhoan);
        params.put("Password", matkhau);
        params.put("Name", name);
        params.put("Email", email);
        params.put("Image",imageurl);
        register(params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }
    private void InsertData(String tk, String mk, String ten, String email, String url) {
        String sql = "INSERT INTO tbNguoiDung(TaiKhoan, MatKhau, Ten, Email, ImageURL) VALUES('"+tk+"','"+mk+"','"+ten+"','"+email+"','"+url+"')";
        db.execSQL(sql);
    }
    private void DeleteData(){
        String sql = "DELETE FROM tbNguoiDung";
        db.execSQL(sql);
    }
}
