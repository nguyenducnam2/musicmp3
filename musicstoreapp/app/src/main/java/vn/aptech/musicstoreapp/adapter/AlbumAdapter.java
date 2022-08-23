package vn.aptech.musicstoreapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.activity.ListSongActivity;
import vn.aptech.musicstoreapp.entity.Album;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> mangbangxephang;
    View view;

    public AlbumAdapter(Context context, ArrayList<Album> mangbangxephang) {
        this.context = context;
        this.mangbangxephang = mangbangxephang;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.album_list,parent, false);
        return new AlbumAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, final int position) {
        Album album = mangbangxephang.get(position);
        holder.txtbangxephang.setText(album.getName());
        Picasso.get(/*context*/).load(ApiUtil.WEBDATA_URL+"album/"+album.getImage()).into(holder.imgbangxephang);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("intentbangxephang", mangbangxephang.get(holder.getAdapterPosition()));
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

