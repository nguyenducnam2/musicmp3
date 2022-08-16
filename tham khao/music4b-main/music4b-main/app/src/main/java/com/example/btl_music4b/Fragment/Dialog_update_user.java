package com.example.btl_music4b.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.btl_music4b.Model.ResponseModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dialog_update_user extends DialogFragment {
    ExampleDialogListenerUpdateUser listener;
    private ImageView upload;
    private CircleImageView avt;
    private ImageView choose_image;
    private TextInputEditText edt_name_user;
    private String username ="", name = "", url_img = "";
    int IMG_REQUEST = 21;
    private Bitmap bitmap = null;

    public Dialog_update_user(String username, String name, String url_img){
        this.username = username;
        this.name = name;
        this.url_img = url_img;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update_profile, container, false);
        upload = view.findViewById(R.id.btn_send_update);
        avt = view.findViewById(R.id.img_avt);
        choose_image = view.findViewById(R.id.choose_img);
        edt_name_user = view.findViewById(R.id.txt_update_nameuser);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        actionDialog();
        Init(name, url_img);
        return view;
    }

    private void actionDialog(){
        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }else {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                }*/
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_name_user.getText().toString().equals(name)  && bitmap == null){
                    Toast.makeText(getActivity(), "^.^", Toast.LENGTH_LONG).show();
                }else if (edt_name_user.getText().toString().equals("") || edt_name_user.getText().toString().length() > 15){
                    Toast.makeText(getActivity(), "Tên không được để trống và ít hơn 15 kí tự !", Toast.LENGTH_LONG).show();
                }else{
                    uploadImage();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap((getContext()).getContentResolver(),path);
                avt.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

/*    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
                break;
            }
        }
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            } else {

            }
        }
    }*/

    private void Init(String name, String img){
        edt_name_user.setText(name);
        Picasso.get().load(img).into(avt);
    }

    private void uploadImage() {
        HashMap<String, String> params = new HashMap<>();
        if (bitmap != null){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,75, byteArrayOutputStream);
            byte[] imageInByte = byteArrayOutputStream.toByteArray();
            String encodedImage =  Base64.encodeToString(imageInByte,Base64.DEFAULT);
            params.put("Image", encodedImage);
        }
        params.put("UserName", username);
        params.put("Name", edt_name_user.getText().toString());

        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> call = dataservice.insertimage(params);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                if (responseModel.getSuccess().equals("1")){
                    if (bitmap != null){
                        listener.apply(edt_name_user.getText().toString(), bitmap);
                    }else {
                        listener.apply(edt_name_user.getText().toString(), null);
                    }
                    name = edt_name_user.getText().toString();
                    dismiss();
                }else if (responseModel.getSuccess().equals("0")){
                    Toast.makeText(getActivity(), responseModel.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        try {
            listener = (ExampleDialogListenerUpdateUser) getTargetFragment();
        }catch (Exception e){

        }
        super.onAttach(context);
    }

    public interface ExampleDialogListenerUpdateUser{
        void apply(String tenUser, Bitmap bitmap);
    }
}
