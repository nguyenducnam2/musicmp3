package vn.aptech.musicstoreapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.adapter.ListSongAdapter;
import vn.aptech.musicstoreapp.entity.Album;
import vn.aptech.musicstoreapp.entity.Artist;
import vn.aptech.musicstoreapp.entity.Genre;
import vn.aptech.musicstoreapp.entity.Song;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.AlbumService;
import vn.aptech.musicstoreapp.service_api.service.ArtistService;
import vn.aptech.musicstoreapp.service_api.service.GenreService;

public class ListSongActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    TextView txtcollapsing;
    Artist ngheSi = null;
    Album bangXepHang = null;
    Genre genre;
    ImageView imgdanhsachcakhuc;
    ArrayList<Song> mangbaihat;
    ListSongAdapter danhsachbaihatAdapter;
    ImageView btnThemnhac;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listsong);
        mapping();
        floatingActionButton.setVisibility(View.GONE);
        DataIntent();
        //  overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);


    }

    private void mapping() {
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        txtcollapsing = findViewById(R.id.textViewcollapsing);
        btnThemnhac = findViewById(R.id.btnthemnhacthuvien);
        swipeRefreshLayout = findViewById(R.id.swipedanhsachbaihat);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void DataIntent() {
        Intent intent = getIntent();
        btnThemnhac.setVisibility(View.GONE);
        if (intent != null) {
            if (intent.hasExtra("intentnghesi")) {
                ArtistService dataservice = ApiUtil.getArtistService();
                Call<List<Artist>> callback = dataservice.findAll();
                callback.enqueue(new Callback<List<Artist>>() {
                    @Override
                    public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                        ArrayList<Artist> arr = (ArrayList<Artist>) response.body();
                        for (Artist item : arr) {
                            if (item.getId() == intent.getExtras().getInt("intentnghesi")) {
                                ngheSi = item;
                            }
                        }
                        if (ngheSi != null && !ngheSi.toString().equals("")) {
                            setValueInView(ApiUtil.WEBDATA_URL + "artist/" + ngheSi.getImage());
                            GetDataNgheSi(ngheSi.getId());
                            System.out.println(ngheSi.getId());
                            txtcollapsing.setText(ngheSi.getName());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Artist>> call, Throwable t) {

                    }
                });
            } else if (intent.hasExtra("intentbangxephang")) {
                bangXepHang = (Album) intent.getParcelableExtra("intentbangxephang");
                if (bangXepHang != null && !bangXepHang.toString().equals("")) {
                    setValueInView(ApiUtil.WEBDATA_URL + "album/" + bangXepHang.getImage());
                    GetDataBangXepHang(bangXepHang.getId());
                    txtcollapsing.setText(bangXepHang.getName());
                }
            } else if (intent.hasExtra("intentchude")) {
                genre = (Genre) intent.getParcelableExtra("intentchude");
                if (genre != null && !genre.toString().equals("")) {
                    ApiUtil.getGenreService().getSongById(genre.getId()).enqueue(new Callback<List<Song>>() {
                        @Override
                        public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                            Random random = new Random();
                            setValueInView(ApiUtil.WEBDATA_URL+"artist/"+response.body().get(random.nextInt(response.body().size())).getArtist().getImage());
                        }

                        @Override
                        public void onFailure(Call<List<Song>> call, Throwable t) {

                        }
                    });
                    GetDataChuDe(genre.getId());
                    txtcollapsing.setText(genre.getName());
                }
            }/*else/*else
            if(intent.hasExtra("idthuvienplaylist")){
                thuVienPlayList = (ThuVienPlayListModel) intent.getSerializableExtra("idthuvienplaylist");
                id = thuVienPlayList.getIDThuVienPlayList();
                btnThemnhac.setVisibility(View.VISIBLE);
            } */
        }
    }

    private void setValueInView(String hinh) {
        Picasso.get().load(hinh).into(imgdanhsachcakhuc);
    }

    private void GetDataNgheSi(int id) {
        ArtistService dataservice = ApiUtil.getArtistService();
        Call<List<Song>> callback = dataservice.getSongById(id);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                mangbaihat = (ArrayList<Song>) response.body();
                danhsachbaihatAdapter = new ListSongAdapter(ListSongActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataBangXepHang(int id) {
        AlbumService dataservice = ApiUtil.getAlbumService();
        Call<List<Song>> callback = dataservice.getSongById(id);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                mangbaihat = (ArrayList<Song>) response.body();
                danhsachbaihatAdapter = new ListSongAdapter(ListSongActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataChuDe(int id) {
        GenreService dataservice = ApiUtil.getGenreService();
        System.out.println(id);
        Call<List<Song>> callback = dataservice.getSongById(id);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                mangbaihat = (ArrayList<Song>) response.body();
                danhsachbaihatAdapter = new ListSongAdapter(ListSongActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(ListSongActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
