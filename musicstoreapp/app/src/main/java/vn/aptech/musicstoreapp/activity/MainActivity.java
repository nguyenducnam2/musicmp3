package vn.aptech.musicstoreapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.entity.Genre;
import vn.aptech.musicstoreapp.service_api.service.GenreService;

import vn.aptech.musicstoreapp.R;

public class MainActivity extends AppCompatActivity {

    private TextView tvDemo;
    private GenreService genreService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDemo=findViewById(R.id.tvDemo);
        genreService= ApiUtil.getGenreService();
        genreService.findAll().enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                tvDemo.setText(response.body().get(1).getName());
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

            }
        });

    }
}