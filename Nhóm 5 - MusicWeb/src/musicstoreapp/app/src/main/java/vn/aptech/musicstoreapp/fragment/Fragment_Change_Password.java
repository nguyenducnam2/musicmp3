package vn.aptech.musicstoreapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.activity.LoginActivity;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.AccountService;

public class Fragment_Change_Password extends Fragment {

    View view;
    EditText newPassword, passwordConfirm;
    Button btnConfirm;
    private String username;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment__change__password, container, false);

        newPassword = view.findViewById(R.id.edNewPassword);
        passwordConfirm = view.findViewById(R.id.edPasswordConfirm);
        btnConfirm = view.findViewById(R.id.btnConfirm);

        Bundle bundle = getArguments();
        if (bundle !=null){
            username = bundle.getString("username");
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                loadingDialog.StartLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 2000);

                if (newPassword.getText().toString().equals(passwordConfirm.getText().toString())){
                    updatePassword(username, newPassword.getText().toString());
                }else {
                    Toast.makeText(getActivity(), "Password not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void updatePassword(String username, String password) {
        AccountService dataService = ApiUtil.getAccountService();
        Call<ResponseBody> callback = dataService.resetPasswordAndroid(username, password);
        callback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String message = response.body().toString();
                System.out.println("------------------"+message);
//                String message1 = call.toString();
//                System.out.println("------------------"+message1);
                if (message != null) {
//                    if (message.equals("success")) {
                        Toast.makeText(getActivity(), "Password change success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    } else {
//                        Log.i("mess",message);
                        Toast.makeText(getActivity(),"message.toString()" , Toast.LENGTH_SHORT).show();
                    }
//                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }

        });
    }
}