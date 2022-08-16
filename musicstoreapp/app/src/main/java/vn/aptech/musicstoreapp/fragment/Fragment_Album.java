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
import vn.aptech.musicstoreapp.adapter.AlbumAdapter;
import vn.aptech.musicstoreapp.entity.Album;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.AlbumService;

public class Fragment_Album extends Fragment {
    View view;
    AlbumAdapter bangXepHangAdapter;
    RecyclerView recyclerViewbangxephang;
    TextView tenbangxephang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);
        recyclerViewbangxephang = view.findViewById(R.id.recyclerviewbangxephang);
        tenbangxephang = view.findViewById(R.id.txtbangxephang);
        GetData();
        return view;
    }

    private void GetData() {
        AlbumService dataservice = ApiUtil.getAlbumService();
        Call<List<Album>> callback = dataservice.findAll();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangbangxephang = (ArrayList<Album>) response.body();
                bangXepHangAdapter = new AlbumAdapter(getActivity(), mangbangxephang);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewbangxephang.setLayoutManager(linearLayoutManager);
                recyclerViewbangxephang.setAdapter(bangXepHangAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

}
