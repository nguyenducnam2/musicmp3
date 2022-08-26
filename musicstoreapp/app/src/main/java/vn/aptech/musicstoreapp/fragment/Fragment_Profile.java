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
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;

public class Fragment_Profile extends Fragment {

    Button btnLogout;
    TextView acc, pass, tvUsername;
    de.hdodenhof.circleimageview.CircleImageView imgUserProfile;
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
        String ful_name = sharedPreferences.getString("ful_name","");
        String img = sharedPreferences.getString("image","");
        tvUsername.setText("Hi "+ful_name);
        Picasso.get(/*context*/).load(ApiUtil.WEBDATA_URL + "user/" + img).into(imgUserProfile);
//        url.setText(sharedPreferences.getString("image",""));
//        System.out.println("tvUsername ");
        System.out.println("tvUsername "+ful_name);
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
        tvUsername = view.findViewById(R.id.tvUsernameProfile);
        imgUserProfile = view.findViewById(R.id.imgUserProfile);
    }
//    private void ShowDialogUpdate(){
//        DialogUpdateUser dialog_update_user = new DialogUpdateUser();
//        dialog_update_user.show(getParentFragmentManager(), "");
//    }


}