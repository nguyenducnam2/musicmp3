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
import com.example.btl_music4b.Model.PhoBienModel;
import com.example.btl_music4b.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhoBienAdapter extends RecyclerView.Adapter<PhoBienAdapter.ViewHolder> {

    Context context;
    ArrayList<PhoBienModel> arrayListphobien;
    View view;

    public PhoBienAdapter(Context context, ArrayList<PhoBienModel> arrayListphobien) {
        this.context = context;
        this.arrayListphobien = arrayListphobien;
    }

    @NonNull
    @Override
    public PhoBienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_phobien,parent, false);
        return new PhoBienAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoBienAdapter.ViewHolder holder, final int position) {
        PhoBienModel phoBien = arrayListphobien.get(position);
        holder.txttenphobien.setText(phoBien.getTenPhoBien());
        Picasso.get(/*context*/).load(phoBien.getHinhPhoBien()).into(holder.imgphobien);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                intent.putExtra("intentphobien", arrayListphobien.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListphobien != null ? arrayListphobien.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgphobien;
        TextView txttenphobien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgphobien = itemView.findViewById(R.id.imageviewphobien);
            txttenphobien = itemView.findViewById(R.id.textviewphobien);
        }
    }
}
