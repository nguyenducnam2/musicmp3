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
import vn.aptech.musicstoreapp.activity.PlayMusicActivity;
import vn.aptech.musicstoreapp.entity.Song;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    Context context;
    ArrayList<Song> mangbaihat;

    public SearchAdapter(Context context, ArrayList<Song> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.result_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song baiHat = mangbaihat.get(position);
        holder.txttentimkiem.setText(baiHat.getName());
        holder.txtcasitimkiem.setText(baiHat.getArtist().getName());
        Picasso.get().load(ApiUtil.WEBDATA_URL+"album/"+baiHat.getAlbum().getImage()).into(holder.imganhtimkiem);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txttentimkiem, txtcasitimkiem;
        ImageView imganhtimkiem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttentimkiem = itemView.findViewById(R.id.txttennhac);
            txtcasitimkiem = itemView.findViewById(R.id.txtcasinhac);
            imganhtimkiem = itemView.findViewById(R.id.imgnhac);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }

}
