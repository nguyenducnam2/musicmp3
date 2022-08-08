package com.example.btl_music4b.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.btl_music4b.Activity.DangKyActivity;
import com.example.btl_music4b.Model.ResponseModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Doi_MatKhau extends Fragment {

    View view;
    EditText matkhaumoi, xacnhanmatkhau;
    TextView textView;
    Button btnxacnhan;
    private String taikhoan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_doi_matkhau, container, false);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.anim_intent_in, R.anim.anim_intent_out);;
        matkhaumoi = view.findViewById(R.id.edtmatkhaumoi);
        xacnhanmatkhau = view.findViewById(R.id.edtxacnhanmatkhau);
        textView = view.findViewById(R.id.textView9);
        btnxacnhan = view.findViewById(R.id.buttonxacnhan);

        Bundle bundle = getArguments();
        if (bundle !=null){
            taikhoan = bundle.getString("taikhoan");
        }

        btnxacnhan.setOnClickListener(new View.OnClickListener() {
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

                if (matkhaumoi.getText().toString().equals(xacnhanmatkhau.getText().toString())){
                    UpdatePassword(taikhoan, matkhaumoi.getText().toString());
                }else {
                    Toast.makeText(getActivity(), "Mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public void UpdatePassword(String username, String password) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.updatepasswordnguoidung(username, password);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(getActivity(), "Đã đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), DangKyActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "erro", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }

        });
    }
}
