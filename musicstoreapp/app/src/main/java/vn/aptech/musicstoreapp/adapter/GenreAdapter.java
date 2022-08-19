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
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.activity.ListSongActivity;
import vn.aptech.musicstoreapp.entity.Genre;
import vn.aptech.musicstoreapp.entity.Song;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.SongService;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder>{

    Context context;
    ArrayList<Genre> mangchude;
    View view;

    public GenreAdapter(Context context, ArrayList<Genre> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.genre_list,parent, false);
        return new GenreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.ViewHolder holder, final int position) {
        Genre chuDe = mangchude.get(position);
        holder.txttenchude.setText(chuDe.getName());
        ApiUtil.getGenreService().getSongById(chuDe.getId()).enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                Random random=new Random();
                Picasso.get(/*context*/).load(ApiUtil.WEBDATA_URL+"artist/"+response.body().get(random.nextInt(response.body().size())).getArtist().getImage()).into(holder.imgchude);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("intentchude", mangchude.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangchude != null ? mangchude.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgchude;
        TextView txttenchude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchude = itemView.findViewById(R.id.imageviewchude);
            txttenchude = itemView.findViewById(R.id.textviewchude);
        }
    }
}
