package com.example.btl_music4b.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_music4b.Activity.DangKyActivity;
import com.example.btl_music4b.R;


public class Dialog_Forget_Password extends AppCompatActivity {

    ImageView imgclose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_forget_password);
        imgclose = findViewById(R.id.imageCloseqmk);
        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dialog_Forget_Password.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }

}
