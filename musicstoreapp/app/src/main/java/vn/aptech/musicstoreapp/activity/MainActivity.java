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
        String sql = "CREATE TABLE IF NOT EXISTS account(id integer primary key autoincrement,username text, password text,fullname text, role text, image text)";
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


    private void register(HashMap<String, String> params) {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AccountService dataService = ApiUtil.getAccountService();
        Call<ResponseBody> registerCall = dataService.registerAndroid( params);
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
//        Dataservice networkService = APIService.getService();
//        Call<ResponseBody> registerCall = networkService.register(params);
//        registerCall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
//                ResponseBody responseBody = response.body();
//                if (responseBody != null) {
//                    if (responseBody.getSuccess().equals("1")) {
//                        if (flag == 1){
//                            InsertData(username, password, username, email, imageurl);
//                        }else if (flag == 2){
//                            InsertData(username, password, name, email, imageurl);
//                        }
//                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                        startActivity(intent);
//                    } else if (responseBody.getSuccess().equals("0")){
//                        if (flag == 1){ //dang ky free
//                            Toast.makeText(MainActivity.this, "Tài khoản đã được đăng ký !", Toast.LENGTH_LONG).show();
//                        }else if (flag == 2){ // dang nhap bang facebook
//                            Toast.makeText(MainActivity.this, "Đăng nhập bằng facebook !", Toast.LENGTH_LONG).show();
//                            InsertData(username, password, name, email, imageurl);
//                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                            startActivity(intent);
//                        }
//                    }
//                }
//                progressDialog.dismiss();
//            }
//            @Override
//            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
//                progressDialog.dismiss();
//            }
//        });
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        if (flag == 2){
//            GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
//                    new GraphRequest.GraphJSONObjectCallback() {
//                        @Override
//                        public void onCompleted(JSONObject object, GraphResponse response) {
//                            try {
//                                username = object.getString("id");
//                                password = "LhKbEpmsWkl56J00r34vcnmTg";
//                                name = object.getString("name");
//                                email = "-";
//                                imageurl = "https://graph.facebook.com/"+username+"/picture?style=large";
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            HashMap<String, String> params = new HashMap<>();
//                            params.put("UserName", username);
//                            params.put("Password", password);
//                            params.put("Name", name);
//                            params.put("Email", email);
//                            params.put("Image", imageurl);
//                            register(params);
//                        }
//                    });
//
//            Bundle bundle = new Bundle();
//            bundle.putString("fields", "name, id");
//
//            graphRequest.setParameters(bundle);
//            graphRequest.executeAsync();
//        }
//    }

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
        HashMap<String, String> params = new HashMap<>();
        username = username;
        password = password;
        params.put("UserName", username);
        params.put("Password", password);
        register(params);
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