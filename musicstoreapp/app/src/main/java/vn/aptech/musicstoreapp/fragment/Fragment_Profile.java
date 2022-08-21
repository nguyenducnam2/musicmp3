//package vn.aptech.musicstoreapp.fragment;
//
//import static android.content.Context.MODE_PRIVATE;
//
//import android.content.Intent;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//import vn.aptech.musicstoreapp.R;
//import vn.aptech.musicstoreapp.activity.HomeActivity;
//import vn.aptech.musicstoreapp.activity.MainActivity;
//
//public class Fragment_Profile extends Fragment {
//
//    Button btn;
//    TextView acc, pass, name, url;
//    HomeActivity hm;
//    CircleImageView imguser;
//    String sql = "";
//    private SQLiteDatabase db;
//    View view;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_profile, container, false);
//        db = getActivity().openOrCreateDatabase("NguoiDung.db", MODE_PRIVATE, null);
//        AnhXa();
//        Init();
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//                hm.finish();
//            }
//        });
//        imguser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShowDialogUpdate();
//            }
//        });
//        return  view;
//    }
//
//    private void Init() {
//        name = view.findViewById(R.id.tennguoidung);
//        hm = (HomeActivity) getActivity();
//        name.setText(hm.getName());
//        Picasso.get().load(hm.getUrl()).into(imguser);
//    }
//
//    private void AnhXa() {
//        btn = view.findViewById(R.id.btndangxuat);
//        name = view.findViewById(R.id.tennguoidung);
//        imguser = view.findViewById(R.id.imageviewuserprofile);
//    }
//    private void ShowDialogUpdate(){
//        DialogUpdateUser dialog_update_user = new DialogUpdateUser(hm.getTaikhoan(), hm.getName(), hm.getUrl());
//        dialog_update_user.show(getParentFragmentManager(), "");
//    }
//
//    @Override
//    public void apply(String tenUser, Bitmap bitmap) {
//        name.setText(tenUser);
//        if (bitmap != null){
//            imguser.setImageBitmap(bitmap);
//            sql = "UPDATE tbNguoiDung SET Ten ='"+tenUser+"', ImageURL =  'https://music4b.000webhostapp.com/HinhAnh/NguoiDung/"+hm.getTaikhoan()+".jpg' WHERE TaiKhoan = '"+hm.getTaikhoan()+"'";
//        }else {
//            sql = "UPDATE tbNguoiDung SET Ten ='"+tenUser+"' WHERE TaiKhoan = '"+hm.getTaikhoan()+"'";
//        }
//        db.execSQL(sql);
//        hm.updateHome();
//    }
//}