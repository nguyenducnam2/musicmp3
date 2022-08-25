package vn.aptech.musicstoreapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.adapter.GenreAdapter;
import vn.aptech.musicstoreapp.entity.Genre;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.GenreService;

public class Fragment_Genre extends Fragment {

    View view;
    GenreAdapter chuDeAdapter;
    RecyclerView recyclerViewChuDe;
    TextView tenChuDe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_genre, container, false);
        recyclerViewChuDe = view.findViewById(R.id.recyclerviewchude);
        tenChuDe = view.findViewById(R.id.txtchude);
        GetData();
        return view;
    }

    private void GetData() {
        GenreService dataservice = ApiUtil.getGenreService();
        Call<List<Genre>> callback = dataservice.findAll();
        callback.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                ArrayList<Genre> mangchude = (ArrayList<Genre>) response.body();
                chuDeAdapter = new GenreAdapter(getActivity(), mangchude);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewChuDe.setLayoutManager(linearLayoutManager);
                recyclerViewChuDe.setAdapter(chuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

            }
        });
    }

}
