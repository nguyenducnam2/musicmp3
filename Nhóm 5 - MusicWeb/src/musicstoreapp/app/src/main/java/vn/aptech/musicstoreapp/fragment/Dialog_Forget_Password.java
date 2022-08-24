package vn.aptech.musicstoreapp.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import vn.aptech.musicstoreapp.R;
import vn.aptech.musicstoreapp.activity.MainActivity;

public class Dialog_Forget_Password extends AppCompatActivity {


    ImageView imgclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_forget_password);
        imgclose = findViewById(R.id.imageCloseForgetPassword);
//        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dialog_Forget_Password.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}