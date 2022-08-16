package com.example.btl_music4b.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_music4b.Model.BaiHatModel;
import com.example.btl_music4b.Model.ResponseModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertNhacThuVienAdapter extends RecyclerView.Adapter<InsertNhacThuVienAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHatModel> mangbaihat;
    int idthuvien;

    public InsertNhacThuVienAdapter(Context context, ArrayList<BaiHatModel> mangbaihat, int idthuvien) {
        this.context = context;
        this.mangbaihat = mangbaihat;
        this.idthuvien = idthuvien;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_tim_kiem_nhac_thuvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHatModel baiHat = mangbaihat.get(position);
        holder.tenbaihat.setText(baiHat.getTenBaiHat());
        holder.temcasi.setText(baiHat.getTenCaSi());
        Picasso.get(/*context*/).load(baiHat.getHinhBaiHat()).into(holder.imgtimkiem);
        holder.imginsertnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDataBaiHatThuVien(idthuvien, baiHat.getIdBaiHat(), baiHat.getTenBaiHat(),baiHat.getTenCaSi(),
                        baiHat.getHinhBaiHat(), baiHat.getLinkBaiHat());
                UpdateHinhThuVien(idthuvien, mangbaihat.get(position).getHinhBaiHat());
            }
        });

    }
    public void InsertDataBaiHatThuVien(int idtv, int idbh, String tbh, String tcs, String hbh, String lbh) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.insertnhacthuvien(idtv, idbh, tbh, tcs, hbh, lbh);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(context, "Đã thêm", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }

        });
    }

    public void UpdateHinhThuVien(int idtv, String hbh) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.updatehinhthuvien(idtv, hbh);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Log.d("updatehinhthuven", "suscess");
                    } else {
                        Log.d("updatehinhthuven", "erro");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }

        });
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tenbaihat, temcasi;
        ImageView imgtimkiem, imginsertnhac;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenbaihat = itemView.findViewById(R.id.txttennhacthuvien);
            temcasi = itemView.findViewById(R.id.txtcasinhacthuvien);
            imgtimkiem = itemView.findViewById(R.id.imgnhacthuvien);
            imginsertnhac = itemView.findViewById(R.id.imginsertnhacthuvien);

        }
    }
}
