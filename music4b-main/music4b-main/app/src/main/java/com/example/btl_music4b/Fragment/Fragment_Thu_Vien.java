package com.example.btl_music4b.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.btl_music4b.Activity.DanhsachbaihatActivity;
import com.example.btl_music4b.Activity.HomeActivity;
import com.example.btl_music4b.Adapter.ViewPagerThuVien;
import com.example.btl_music4b.Model.PhanHoiDangKyModel;
import com.example.btl_music4b.Model.ThuVienPlayListModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Thu_Vien extends Fragment implements Dialog_insert_thu_vien_playlist.ExampleDialogListenerthuvien{
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView imgAddThuVien;
    CircleImageView imguser;
    ProgressDialog progressDialog;
    View view;
    private String tenThuVien;
    private HomeActivity hm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thu_vien, container, false);
        AnhXa();
        init();
        imgAddThuVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        return  view;
    }
    private void init() {
        ViewPagerThuVien viewPagerThuVien = new ViewPagerThuVien(getChildFragmentManager());
        viewPagerThuVien.addFragment(new Fragment_ThuVien_Playlist(), "Playlist");
        viewPagerThuVien.addFragment(new Fragment_ThuVien_NgheSi(), "Nghệ sĩ");
        viewPagerThuVien.addFragment(new Fragment_ThuVien_YeuThich(), "Yêu thích");
        viewPager.setAdapter(viewPagerThuVien);
        tabLayout.setupWithViewPager(viewPager);
        Picasso.get().load(hm.getUrl()).into(imguser);
    }

    private void AnhXa() {
        hm = (HomeActivity) getActivity();
        tabLayout = view.findViewById(R.id.tabLayouttv);
        viewPager = view.findViewById(R.id.viewPagertv);
        imgAddThuVien = view.findViewById(R.id.idaddthuvien);
        imguser = view.findViewById(R.id.imageviewuserthuvien);
    }
    private void openDialog() {
        Dialog_insert_thu_vien_playlist exampleDialog = new Dialog_insert_thu_vien_playlist();
        exampleDialog.show(getFragmentManager(), "Dialog_insert_thu_vien_playlist");
        exampleDialog.setTargetFragment(Fragment_Thu_Vien.this, 1);
    }

    @Override
    public void apply(String tenthuvien) {
        HashMap<String, String> params = new HashMap<>();
        tenThuVien = tenthuvien;
        params.put("tenthuvien", tenThuVien);
        params.put("UserName", hm.getTaikhoan());
        insertthuvien(params);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GetData();
            }
        }, 3000);
    }
    private void insertthuvien(HashMap<String, String> params) {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Creating...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Dataservice networkService = APIService.getService();
        Call<PhanHoiDangKyModel> registerCall = networkService.insertthuvien(params);
        registerCall.enqueue(new Callback<PhanHoiDangKyModel>() {
            @Override
            public void onResponse(@NonNull Call<PhanHoiDangKyModel> call, @NonNull Response<PhanHoiDangKyModel> response) {
                PhanHoiDangKyModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(getActivity(), "Tạo thành công !", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhanHoiDangKyModel> call, @NonNull Throwable t) {
            }
        });
    }
    private void GetData() {
        hm = (HomeActivity) getActivity();
        Dataservice dataservice = APIService.getService();
        Call<List<ThuVienPlayListModel>> callback = dataservice.GetBangThuVienPlayList(hm.getTaikhoan());
        callback.enqueue(new Callback<List<ThuVienPlayListModel>>() {
            @Override
            public void onResponse(Call<List<ThuVienPlayListModel>> call, Response<List<ThuVienPlayListModel>> response) {
                ArrayList<ThuVienPlayListModel> mangthuvienplaylist = (ArrayList<ThuVienPlayListModel>) response.body();
                ThuVienPlayListModel thuVienPlayList = mangthuvienplaylist.get(mangthuvienplaylist.size()-1);

                progressDialog.dismiss();
                Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                intent.putExtra("idthuvienplaylist", thuVienPlayList);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<ThuVienPlayListModel>> call, Throwable t) {

            }

        });
    }

}
