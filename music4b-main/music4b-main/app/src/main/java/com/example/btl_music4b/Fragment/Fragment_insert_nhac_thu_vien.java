package com.example.btl_music4b.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_music4b.Activity.InsertNhacThuVienActivity;
import com.example.btl_music4b.Adapter.InsertNhacThuVienAdapter;
import com.example.btl_music4b.Model.BaiHatModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_insert_nhac_thu_vien extends Fragment {
    View view;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewtim;
    TextView textViewnull;
    InsertNhacThuVienAdapter nhacThuVienAdapter;
    ArrayList<BaiHatModel> mangbaihat;
    InsertNhacThuVienActivity intvA;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_insert_nhac_thu_vien, container, false);
        toolbar = view.findViewById(R.id.toilbartimkiemthuvien);
        recyclerViewtim = view.findViewById(R.id.recyclerviewtimkiemthuvien);
        textViewnull = view.findViewById(R.id.textviewtimkiemnullthuvien);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity)getActivity()).finish();
            }
        });
        setHasOptionsMenu(true);
        return  view;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_timkiem, menu);
        MenuItem menuItem = menu.findItem(R.id.menutimkiem);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setBackgroundColor(Color.WHITE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerViewtim.setBackgroundColor(Color.BLACK);
                if (!s.trim().equals("")){
                    TimKiemBaiHat(s);
                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void TimKiemBaiHat(String query){
        intvA = (InsertNhacThuVienActivity) getActivity();
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHatModel>> callback = dataservice.GetTimKiembaihat(query);
        callback.enqueue(new Callback<List<BaiHatModel>>() {
            @Override
            public void onResponse(Call<List<BaiHatModel>> call, Response<List<BaiHatModel>> response) {
                mangbaihat = (ArrayList<BaiHatModel>) response.body();
                if (mangbaihat.size() > 0){
                    nhacThuVienAdapter = new InsertNhacThuVienAdapter(getActivity(), mangbaihat, intvA.getId());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewtim.setLayoutManager(linearLayoutManager);
                    recyclerViewtim.setAdapter(nhacThuVienAdapter);
                    textViewnull.setVisibility(View.GONE);
                    recyclerViewtim.setVisibility(View.VISIBLE);
                }else {
                    recyclerViewtim.setVisibility(View.GONE);
                    textViewnull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHatModel>> call, Throwable t) {

            }
        });
    }
}
