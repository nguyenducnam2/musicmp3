package com.example.btl_music4b.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_music4b.Activity.DanhsachbaihatActivity;
import com.example.btl_music4b.Model.BangXepHangModel;
import com.example.btl_music4b.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BangXepHangAdapter extends RecyclerView.Adapter<BangXepHangAdapter.ViewHolder>{

    Context context;
    ArrayList<BangXepHangModel> mangbangxephang;
    View view;

    public BangXepHangAdapter(Context context, ArrayList<BangXepHangModel> mangbangxephang) {
        this.context = context;
        this.mangbangxephang = mangbangxephang;
    }

    @NonNull
    @Override
    public BangXepHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_bangxephang,parent, false);
        return new BangXepHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BangXepHangAdapter.ViewHolder holder, final int position) {
        BangXepHangModel bangXepHang = mangbangxephang.get(position);
        holder.txtbangxephang.setText(bangXepHang.getTenBangXepHang());
        Picasso.get(/*context*/).load(bangXepHang.getHinhBangXepHang()).into(holder.imgbangxephang);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("intentbangxephang", mangbangxephang.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangbangxephang != null ? mangbangxephang.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgbangxephang;
        TextView txtbangxephang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbangxephang = itemView.findViewById(R.id.imageviewbangxephang);
            txtbangxephang = itemView.findViewById(R.id.textviewbangxephang);
        }
    }
}
