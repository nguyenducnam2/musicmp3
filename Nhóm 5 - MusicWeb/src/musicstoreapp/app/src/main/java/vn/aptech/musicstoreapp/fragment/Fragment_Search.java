package vn.aptech.musicstoreapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.adapter.SearchAdapter;
import vn.aptech.musicstoreapp.entity.Song;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.SongService;

public class Fragment_Search extends Fragment {

    View view;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewtim;
    TextView textViewnull;
    SearchAdapter timKiemAdapter;
    ArrayList<Song> mangbaihat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        toolbar = view.findViewById(R.id.toilbartimkiem);
        recyclerViewtim = view.findViewById(R.id.recyclerviewtimkiem);
        textViewnull = view.findViewById(R.id.textviewtimkiemnull);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return  view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.menutimkiem);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerViewtim.setBackgroundColor(Color.BLACK);
                if (!s.trim().equals("")){
                    TimKiemBaiHat(s);
                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void TimKiemBaiHat(String query){
        SongService dataservice = ApiUtil.getSongService();
        Call<List<Song>> callback = dataservice.getSongByName(query);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                mangbaihat = (ArrayList<Song>) response.body();
                if (mangbaihat.size() > 0){
                    timKiemAdapter = new SearchAdapter(getActivity(), mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewtim.setLayoutManager(linearLayoutManager);
                    recyclerViewtim.setAdapter(timKiemAdapter);
                    textViewnull.setVisibility(View.GONE);
                    recyclerViewtim.setVisibility(View.VISIBLE);
                }else {
                    recyclerViewtim.setVisibility(View.GONE);
                    textViewnull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

}
