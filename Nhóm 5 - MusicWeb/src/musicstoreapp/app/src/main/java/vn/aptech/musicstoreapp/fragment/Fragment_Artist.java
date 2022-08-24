package vn.aptech.musicstoreapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.adapter.ArtistAdapter;
import vn.aptech.musicstoreapp.entity.Artist;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.ArtistService;

public class Fragment_Artist extends Fragment {
    View view;
    ArtistAdapter ngheSiAdapter;
    RecyclerView recyclerViewNgheSi;
    TextView tenNgheSi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_artist, container, false);
        recyclerViewNgheSi = view.findViewById(R.id.recyclerviewnghesi);
        tenNgheSi = view.findViewById(R.id.txtnghesi);
        GetData();
        return view;
    }

    private void GetData() {
        ArtistService dataservice = ApiUtil.getArtistService();
        Call<List<Artist>> callback = dataservice.findAll();
        callback.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                ArrayList<Artist> mangnghesi = (ArrayList<Artist>) response.body();
                ngheSiAdapter = new ArtistAdapter(getActivity(), mangnghesi);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewNgheSi.setLayoutManager(linearLayoutManager);
                recyclerViewNgheSi.setAdapter(ngheSiAdapter);
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {

            }

        });
    }

}
