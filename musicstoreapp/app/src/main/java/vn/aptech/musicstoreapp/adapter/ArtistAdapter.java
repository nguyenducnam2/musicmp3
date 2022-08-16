package vn.aptech.musicstoreapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.activity.ListSongActivity;
import vn.aptech.musicstoreapp.entity.Artist;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    Context context;
    ArrayList<Artist> mangnghesi;
    View view;

    public ArtistAdapter(Context context, ArrayList<Artist> mangnghesi) {
        this.context = context;
        this.mangnghesi = mangnghesi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.artist_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Artist ngheSi = mangnghesi.get(position);
        holder.txttennghesi.setText(ngheSi.getName());
        Picasso.get(/*context*/).load(ApiUtil.WEBDATA_URL + "artist/" + ngheSi.getImage()).into(holder.imgnghesi);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("intentnghesi", mangnghesi.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangnghesi != null ? mangnghesi.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView imgnghesi;
        TextView txttennghesi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgnghesi = itemView.findViewById(R.id.imageviewnghesi);
            txttennghesi = itemView.findViewById(R.id.textviewnghesi);
        }
    }
}
