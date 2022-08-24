package vn.aptech.musicstoreapp.fragment;

import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.activity.HomeActivity;
import vn.aptech.musicstoreapp.activity.MainActivity;

public class Fragment_Profile extends Fragment {

    private String username1;
    Button btnLogout;
    TextView acc, pass, username, url;
    HomeActivity hm;
    CircleImageView imgUser;
    String sql = "";
    private SQLiteDatabase db;
    View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        mapping();


        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("application", Context.MODE_PRIVATE);
//        username.setText(sharedPreferences.getString("full_name",""));
//        url.setText(sharedPreferences.getString("image",""));

        System.out.println("username "+username1);
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent it = new Intent(this.getActivity(), MainActivity.class);
            startActivity(it);
        });

        return  view;
    }



    private void mapping() {
        btnLogout = view.findViewById(R.id.btnLogoutProfile);
//       Z
    }
//    private void ShowDialogUpdate(){
//        DialogUpdateUser dialog_update_user = new DialogUpdateUser();
//        dialog_update_user.show(getParentFragmentManager(), "");
//    }


}