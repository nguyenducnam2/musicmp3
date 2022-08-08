package com.example.btl_music4b.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_music4b.Activity.PlayNhacActivity;
import com.example.btl_music4b.Model.BaiHatThuVienPlayListModel;
import com.example.btl_music4b.Model.ResponseModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dsbhthuvienplaylistAdapter extends RecyclerView.Adapter<dsbhthuvienplaylistAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHatThuVienPlayListModel> mangbaihatthuvienplaylist;
    View view;

    public dsbhthuvienplaylistAdapter(Context context, ArrayList<BaiHatThuVienPlayListModel> mangbaihatthuvienplaylist) {
        this.context = context;
        this.mangbaihatthuvienplaylist = mangbaihatthuvienplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_danh_sach_bai_hat, parent, false);
        return new dsbhthuvienplaylistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHatThuVienPlayListModel baiHatThuVienPlayList = mangbaihatthuvienplaylist.get(position);
        holder.txttenbaihat.setText(baiHatThuVienPlayList.getTenBaiHat());
        holder.txttencasi.setText(baiHatThuVienPlayList.getTenCaSi());
        Picasso.get(/*context*/).load(baiHatThuVienPlayList.getHinhBaiHat()).into(holder.hinhbaihat);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Xóa bài hát")
                        .setMessage("Bạn có muốn xóa bài hát "+baiHatThuVienPlayList.getTenBaiHat()+" ?")
                        .setPositiveButton("Xóa", null)
                        .setNegativeButton("Hủy", null)
                        .show();

                Button pos = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button neg = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                pos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deletemotbaihatthuvien(baiHatThuVienPlayList.getIdBaiHatThuVienPlayList());
                        mangbaihatthuvienplaylist.remove(position);
                        if (mangbaihatthuvienplaylist.size() <= 0){
                            UpdateHinhThuVien(baiHatThuVienPlayList.getIdThuVienPlayList(), "https://music4b.000webhostapp.com/icon_thuvien.jpg");
                        }else {
                            if (position == mangbaihatthuvienplaylist.size()){
                                UpdateHinhThuVien(baiHatThuVienPlayList.getIdThuVienPlayList(), mangbaihatthuvienplaylist.get(mangbaihatthuvienplaylist.size()-1).getHinhBaiHat());
                            }
                        }
                        alertDialog.dismiss();
                    }
                });
                neg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                return false;
            }
        });
    }

    private void deletemotbaihatthuvien(int idbaihatthuvien) {

        Dataservice networkService = APIService.getService();
        Call<ResponseModel> login = networkService.deletemotbaihatthuvien(idbaihatthuvien);
        login.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Bài hát này đã được xóa", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {

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
        return mangbaihatthuvienplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttenbaihat, txttencasi;
        ImageView hinhbaihat, tim;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenbaihat = itemView.findViewById(R.id.textViewtenbaihat);
            txttencasi = itemView.findViewById(R.id.textViewtencasi);
            hinhbaihat = itemView.findViewById(R.id.imageViewhinhbaihat);
            tim = itemView.findViewById(R.id.imageViewtimdanhsachbaihat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhucthuvien", mangbaihatthuvienplaylist.get(getAdapterPosition()));
                    context.startActivity(intent);

                }
            });
        }
    }
}
