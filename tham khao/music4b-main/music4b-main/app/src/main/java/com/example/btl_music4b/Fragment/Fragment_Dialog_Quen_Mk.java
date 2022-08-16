package com.example.btl_music4b.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.btl_music4b.Model.NguoiDungModel;
import com.example.btl_music4b.R;
import com.example.btl_music4b.Service_API.APIService;
import com.example.btl_music4b.Service_API.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Dialog_Quen_Mk extends Fragment {
    View view;
    TextView txtmes;
    EditText editTextTaiKhoan, maxacnhan;
    Button btngetma, btntiep;
    boolean acceptgetcode = false;
    static int interval;
    static Timer timer;
    int delay = 1000, period = 1000, timeValue, code;
    private String emailuser="", taikhoan="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dialog_quen_mk, container, false);
        txtmes = view.findViewById(R.id.mes);
        editTextTaiKhoan = view.findViewById(R.id.edttaikhoanqmk);
        btngetma = view.findViewById(R.id.buttongetcode);
        btntiep = view.findViewById(R.id.buttontiep);
        maxacnhan = view.findViewById(R.id.edtmxn);

        btngetma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setInterval() < 1){
                    acceptgetcode = false;
                    txtmes.setVisibility(View.GONE);
                    final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                    loadingDialog.StartLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                        }
                    }, 5000);

                    taikhoan = editTextTaiKhoan.getText().toString().trim();
                    if(taikhoan.trim().length() < 6 || taikhoan.trim().length() > 36){
                        Toast.makeText(getActivity(), "Độ dài tài khoản từ 6 -> 36 ký tự", Toast.LENGTH_SHORT).show();
                    }else {
                        GetDataUser(taikhoan);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (acceptgetcode){
                                    senMail(emailuser);
                                }
                            }
                        }, 3000);
                    }
                }
            }

        });

        btntiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(maxacnhan.getText().toString().trim().equals(""))){
                    if (code == Integer.parseInt(maxacnhan.getText().toString())){
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                                .setCustomAnimations(R.anim.anim_intent_in, R.anim.anim_intent_out);
                        Fragment_Doi_MatKhau fragment_doi_matKhau = new Fragment_Doi_MatKhau();

                        Bundle bundle = new Bundle();
                        bundle.putString("taikhoan", taikhoan);
                        fragment_doi_matKhau.setArguments(bundle);

                        fragmentTransaction.replace(R.id.frameContent, fragment_doi_matKhau);
                        fragmentTransaction.commit();
                    }else {
                        Toast.makeText(getActivity(), "Mã xác nhận không đúng", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "Bạn chưa nhập mã xác nhận", Toast.LENGTH_SHORT).show();
                }
            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return  view;
    }

    private static final int setInterval() {
        if (interval == 1){
            timer.cancel();
        }
        return --interval;
    }

    private void senMail(String tkqmk){
        final String email = "music4bverify@gmail.com";
        final  String password = "L581f3186";
        Random random = new Random();
        code = 10000 + random.nextInt(89999);
        String messenger = "[Music4B]Your Pin confirm is :"+ code+". Please don't share this code!!!.";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tkqmk));
            message.setSubject("Quên mật khẩu");
            message.setText(messenger);
            Transport.send(message);

            char[] ch = new char[37];
            tkqmk.getChars(0, 3, ch, 0);
            txtmes.setText("Mã xác nhận đã gửi đến "+ch[0]+ch[1]+ch[2]+"***@gmail.com");
            txtmes.setVisibility(View.VISIBLE);
            String secs = "60";
            interval = Integer.parseInt(secs);
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    timeValue = setInterval();
                    if (timeValue == 0){
                        btngetma.setText("Lấy mã");
                    }else {
                        btngetma.setText(""+timeValue);
                    }
                }
            }, delay, period);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
        }

    }

    private void GetDataUser(String username) {
        Dataservice dataservice = APIService.getService();
        Call<List<NguoiDungModel>> callback = dataservice.thongtinnguoidung(username);
        callback.enqueue(new Callback<List<NguoiDungModel>>() {
            @Override
            public void onResponse(Call<List<NguoiDungModel>> call, Response<List<NguoiDungModel>> response) {
                ArrayList<NguoiDungModel> mangthongtinnguoidung = (ArrayList<NguoiDungModel>) response.body();
                if (mangthongtinnguoidung.size() > 0){
                    emailuser = mangthongtinnguoidung.get(0).getEmail();
                    acceptgetcode = true;
                }else {
                    Toast.makeText(getActivity(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<NguoiDungModel>> call, Throwable t) {

            }
        });
    }

}
