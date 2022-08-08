package com.example.muizkapp.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.muizkapp.Model.ResponseModel;
import com.example.muizkapp.R;
import com.example.muizkapp.Service_API.APIService;
import com.example.muizkapp.Service_API.Dataservice;
import com.google.android.material.textfield.TextInputLayout;

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

public class Dialog_Dangky_Free extends AppCompatDialogFragment {

    TextInputLayout tk, mk, emaildangky, maxndky;
    Button btnDangKy, btngetmadangky;
    ImageView imgclose;
    boolean accept = false, aceptmail = false;
    private ExampleDialogListener listener;
    String taikhoan="", matkhau="", email="", maxacnhan="";
    static int interval;
    static Timer timer;
    int delay = 1000, period = 1000, timeValue, code;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_dang_ky_free, null);
        tk = view.findViewById(R.id.edttaikhoan);
        mk = view.findViewById(R.id.edtmatkhau);
        emaildangky = view.findViewById(R.id.emaildangky);
        maxndky = view.findViewById(R.id.edtmaxacnhandangky);
        btnDangKy = view.findViewById(R.id.btndangKy);
        btngetmadangky = view.findViewById(R.id.btngetmadangky);
        imgclose = view.findViewById(R.id.imageClose);
        builder.setView(view);

        btngetmadangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setInterval() < 1){
                    aceptmail = false;
                    final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                    loadingDialog.StartLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                        }
                    }, 5000);

                    email = emaildangky.getEditText().getText().toString().trim();
                    EmailValidator validator = new EmailValidator();

                    if (email.trim().length() < 6 || email.trim().length() > 36){
                        Toast.makeText(getActivity(), "Độ dài email từ 6 -> 36 ký tự", Toast.LENGTH_LONG).show();
                    }else {
                        if (validator.validate(email)) {
                            checkEmail(emaildangky.getEditText().getText().toString().trim());
                        } else {
                            Toast.makeText(getActivity(), "Email không đúng định dạng", Toast.LENGTH_LONG).show();
                        }
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (aceptmail){
                                senMail(email);
                            }
                        }
                    }, 3000);
                }
            }
        });


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept = false;
                final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                loadingDialog.StartLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 3000);

                taikhoan = tk.getEditText().getText().toString().trim();
                matkhau = mk.getEditText().getText().toString().trim();
                maxacnhan = maxndky.getEditText().getText().toString().trim();
                if(taikhoan.trim().length() < 6 || taikhoan.trim().length() > 25){
                    Toast.makeText(getActivity(), "Độ dài tài khoản từ 6 -> 25 ký tự", Toast.LENGTH_LONG).show();
                }else {
                    checkUser(taikhoan);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (accept){
                            if (matkhau.trim().length() < 6 || matkhau.trim().length() > 36){
                                Toast.makeText(getActivity(), "Độ dài mật khẩu từ 6 -> 36 ký tự", Toast.LENGTH_LONG).show();
                            }else if (email.equals(emaildangky.getEditText().getText().toString().trim())){
                                if (maxacnhan.equals(String.valueOf(code))){
                                    listener.apply(taikhoan, matkhau, email);
                                }else {
                                    Toast.makeText(getActivity(), "Mã xác nhận không đúng", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                loadingDialog.dismissDialog();
                                Toast.makeText(getActivity(), "Email này chưa nhận mã", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, 3000);
            }
        });
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }
    private static final int setInterval() {
        if (interval == 1){
            timer.cancel();
        }
        return --interval;
    }
    private void senMail(String emaildangky){
        final String email = "music4bverify@gmail.com";
        final  String password = "L581f3186";
        Random random = new Random();
        code = 10000 + random.nextInt(89999);
        String messenger = "[Music4B]Mã xác nhận của bạn là :"+ code+". Không chia sẻ mã này cho bất kì ai.";
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emaildangky));
            message.setSubject("Đăng ký tài khoản");
            message.setText(messenger);
            Transport.send(message);

            Toast.makeText(getActivity(), "Đã gửi", Toast.LENGTH_SHORT).show();
            String secs = "60";
            interval = Integer.parseInt(secs);
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    timeValue = setInterval();
                    if (timeValue == 0){
                        btngetmadangky.setText("Lấy mã");
                    }else {
                        btngetmadangky.setText(""+timeValue);
                    }
                }
            }, delay, period);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Lỗi kết nối, hãy thử lại sau", Toast.LENGTH_SHORT).show();
        }
    }

    // user
    public void checkUser(String us) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.checkEmail(us);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(getActivity(), "Tài khoản đã được sử dụng", Toast.LENGTH_SHORT).show();
                    } else {
                        accept = true;
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }

        });
    }
    // email
    public void checkEmail(String em) {
        Dataservice dataservice = APIService.getService();
        Call<ResponseModel> callback = dataservice.checkEmail(em);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(getActivity(), "email đã được sử dụng", Toast.LENGTH_SHORT).show();
                    } else {
                        aceptmail = true;
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ExampleDialogListener) context;
    }
    public interface ExampleDialogListener{
        void apply(String taikhoan, String matkhau, String email);
    }
}
