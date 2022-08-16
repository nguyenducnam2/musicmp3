package com.example.btl_music4b.Fragment;

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

import com.example.btl_music4b.Adapter.PhoBienAdapter;
import com.example.btl_music4b.Model.PhoBienModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PhoBien extends Fragment {
    View view;
    PhoBienAdapter phoBienAdapter;
    RecyclerView recyclerViewphobien;
    TextView tenPhoBien;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phobien, container, false);
        recyclerViewphobien = view.findViewById(R.id.recyclerviewphobien);
        tenPhoBien = view.findViewById(R.id.txtphobien);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PhoBienModel>> callback = dataservice.GetPhoBienCurrent();
        callback.enqueue(new Callback<List<PhoBienModel>>() {
            @Override
            public void onResponse(Call<List<PhoBienModel>> call, Response<List<PhoBienModel>> response) {
                ArrayList<PhoBienModel> mangphobien = (ArrayList<PhoBienModel>) response.body();
                phoBienAdapter = new PhoBienAdapter(getActivity(), mangphobien);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewphobien.setLayoutManager(linearLayoutManager);
                recyclerViewphobien.setAdapter(phoBienAdapter);
            }

            @Override
            public void onFailure(Call<List<PhoBienModel>> call, Throwable t) {

            }

        });
    }
}
