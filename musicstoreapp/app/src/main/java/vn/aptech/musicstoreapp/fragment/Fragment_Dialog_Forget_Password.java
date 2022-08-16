package vn.aptech.musicstoreapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import vn.aptech.musicstoreapp.R;

public class Fragment_Dialog_Forget_Password extends Fragment {

    View view;
    TextView tvMess;
    EditText edUsernameFP, edPinConfirm;
    Button btnGetPin, btnNext;
    boolean acceptGetCode = false;
    static int interval;
    static Timer timer;
    int delay = 1000, period = 1000, timeValue, code;
    private String emailuser="", taikhoan="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__dialog__forget__password, container, false);
        tvMess = view.findViewById(R.id.mes);
        edUsernameFP = view.findViewById(R.id.edUsernameForgetPassword);
        btnGetPin = view.findViewById(R.id.btnGetCode);
        btnNext = view.findViewById(R.id.btnNext);
        edPinConfirm = view.findViewById(R.id.edPinConfirm);

        btnGetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setInterval() < 1){
                    acceptGetCode = false;
                    tvMess.setVisibility(View.GONE);
                    final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                    loadingDialog.StartLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                        }
                    }, 5000);

                    taikhoan = edUsernameFP.getText().toString().trim();
                    if(taikhoan.trim().length() < 6 || taikhoan.trim().length() > 36){
                        Toast.makeText(getActivity(), "Độ dài tài khoản từ 6 -> 36 ký tự", Toast.LENGTH_SHORT).show();
                    }else {
                        GetDataUser(taikhoan);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (acceptGetCode){
                                    senMail(emailuser);
                                }
                            }
                        }, 3000);
                    }
                }
            }

        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(edPinConfirm.getText().toString().trim().equals(""))){
                    if (code == Integer.parseInt(edPinConfirm.getText().toString())){
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                                .setCustomAnimations(R.anim.anim_intent_in, R.anim.anim_intent_out);
                        Fragment_Dialog_Forget_Password fragmentForgetPassword = new Fragment_Dialog_Forget_Password();

                        Bundle bundle = new Bundle();
                        bundle.putString("taikhoan", taikhoan);
                        fragmentForgetPassword.setArguments(bundle);

                        fragmentTransaction.replace(R.id.frameContent, fragmentForgetPassword);
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

    private void senMail(String username) {
        final String email = "music4bverify@gmail.com";
        final  String password = "L581f3186";
        Random random = new Random();
        code = 10000 + random.nextInt(89999);
        String messenger = "[Muzik App]Your Pin confirm is :"+ code+". Please don't share this code!!!";
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
            message.setSubject("Reset Password");
            message.setText(messenger);
            Transport.send(message);

            char[] ch = new char[37];
            username.getChars(0, 3, ch, 0);
            tvMess.setText("Pin code is sent to "+ch[0]+ch[1]+ch[2]+"***@gmail.com");
            tvMess.setVisibility(View.VISIBLE);
            String secs = "60";
            interval = Integer.parseInt(secs);
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    timeValue = setInterval();
                    if (timeValue == 0){
                        btnGetPin.setText("Get Pin");
                    }else {
                        btnGetPin.setText(""+timeValue);
                    }
                }
            }, delay, period);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Connection error", Toast.LENGTH_SHORT).show();
        }

    }

    private void GetDataUser(String taikhoan) {
//        Dataservice dataservice = APIService.getService();
//        Call<List<NguoiDungModel>> callback = dataservice.thongtinnguoidung(username);
//        callback.enqueue(new Callback<List<NguoiDungModel>>() {
//            @Override
//            public void onResponse(Call<List<NguoiDungModel>> call, Response<List<NguoiDungModel>> response) {
//                ArrayList<NguoiDungModel> mangthongtinnguoidung = (ArrayList<NguoiDungModel>) response.body();
//                if (mangthongtinnguoidung.size() > 0){
//                    emailuser = mangthongtinnguoidung.get(0).getEmail();
//                    acceptGetCode = true;
//                }else {
//                    Toast.makeText(getActivity(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<NguoiDungModel>> call, Throwable t) {
//
//            }
//        });
    }

    private static final int setInterval() {
        if (interval == 1){
            timer.cancel();
        }
        return --interval;
    }
}