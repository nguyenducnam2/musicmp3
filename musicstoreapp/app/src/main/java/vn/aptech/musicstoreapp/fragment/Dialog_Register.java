package vn.aptech.musicstoreapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import vn.aptech.musicstoreapp.entity.ResponseModel;
import vn.aptech.musicstoreapp.service_api.api.ApiUtil;
import vn.aptech.musicstoreapp.service_api.service.AccountService;

public class Dialog_Register extends AppCompatDialogFragment {

    TextView tvMesRegis;
    TextInputLayout edUsername, edPassword, edPinRegister;
    Button btnRegister, btnGetPin;
    ImageView imgClose;
    boolean accept = false;
    private ExampleDialogListener listener;
    String username="", password="", email="", verifyPin="";
    static int interval;
    static Timer timer;
    int delay = 1000, period = 1000, timeValue, code;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_register, null);
        tvMesRegis = view.findViewById(R.id.tvMessRegis);
        edUsername = view.findViewById(R.id.edRegisterUsername);
        edPassword = view.findViewById(R.id.edRegisterPassword);
        edPinRegister = view.findViewById(R.id.edPinRegister);
        btnRegister = view.findViewById(R.id.btnSubmitRegister);
        btnGetPin = view.findViewById(R.id.btnGetPinRegister);
        imgClose = view.findViewById(R.id.imageClose);
        builder.setView(view);

        btnGetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMesRegis.setVisibility(View.GONE);
                if(setInterval() < 1){
                    accept = false;
                    final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
                    loadingDialog.StartLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                        }
                    }, 5000);

                    username = edUsername.getEditText().getText().toString().trim();
                    EmailValidator validator = new EmailValidator();

                    if (edUsername.getEditText().getText().toString().length() == 0) {
                        edUsername.requestFocus();
                        edUsername.setError("Username cannot be blank.");
                        return;
                    }
//                    if (username.trim().length() < 6 || username.trim().length() > 36){
//                        Toast.makeText(getActivity(), "Email length from 6 -> 36 characters", Toast.LENGTH_LONG).show();
//                    }else {
                        if (validator.validate(username)) {
                            checkUser(edUsername.getEditText().getText().toString().trim());
                            System.out.println("---------------accept-btnPin"+accept);
                            if(accept){
                                Toast.makeText(getActivity(), "Wait!! Pin is being sent to "+username, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Invalid Email", Toast.LENGTH_LONG).show();
                        }
//                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (accept){
                                senMail(username);
                            }
                        }
                    }, 3000);
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
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

                if (edUsername.getEditText().getText().toString().length() == 0) {
                    edUsername.requestFocus();
                    edUsername.setError("Username cannot be blank.");
                    return;
                }
                if (edPassword.getEditText().getText().toString().length() == 0) {
                    edPassword.requestFocus();
                    edPassword.setError("Password cannot be blank.");
                    return;
                }
//                if (edPinRegister.getEditText().getText().toString().length() == 0) {
//                    edPinRegister.requestFocus();
//                    edPinRegister.setError("Doesn't have pin yet.");
//                    return;
//                }
                username = edUsername.getEditText().getText().toString().trim();
                password = edPassword.getEditText().getText().toString().trim();
                verifyPin = edPinRegister.getEditText().getText().toString().trim();


//                if(username.trim().length() < 6 || username.trim().length() > 36){
//                    Toast.makeText(getActivity(), "Email's length from 6 -> 36 characters", Toast.LENGTH_LONG).show();
//                }else {
//                    checkUser(username);
                System.out.println("---------------accept-btnRegis"+accept);

//                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (accept){
                            if (password.trim().length() < 3 || password.trim().length() > 36){
                                Toast.makeText(getActivity(), "Password length from 3 -> 36 characters", Toast.LENGTH_LONG).show();
                            }else if (username.equals(edUsername.getEditText().getText().toString().trim())){
                                if (verifyPin.equals(String.valueOf(code))){
                                    listener.apply(username, password);
                                }else {
                                    Toast.makeText(getActivity(), "Verify Pin not correct", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                loadingDialog.dismissDialog();
                                Toast.makeText(getActivity(), "Oops something wrong here!!! Please try late!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, 3000);
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
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

    private void senMail(String receiverEmail){
        try {
        String stringSenderEmail = "sluuthanh.demo.send@gmail.com";
        String stringReceiverEmail = receiverEmail;
        String stringPasswordSenderMail ="nppwzhwzwlapivlk";
        Random random = new Random();
        code = 10000 + random.nextInt(89999);
        String messengerRegisterPin = "Your Pin confirm is :"+ code+". Please don't share this code!!!";

        String stringHost ="smtp.gmail.com";
        String stringPort ="465";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host",stringHost);
        properties.put("mail.smtp.port",stringPort);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderMail);
            }
        });

            Message messageRegister = new MimeMessage(session);
            messageRegister.setFrom(new InternetAddress(stringSenderEmail,"Muzik App"));
            messageRegister.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stringReceiverEmail));
            messageRegister.setSubject("Register Account");
            messageRegister.setText(messengerRegisterPin);
            //gui mail
            Transport.send(messageRegister);


//            char[] ch = new char[37];
//            receiverEmail.getChars(0, 3, ch, 0);


//            tvMesRegis.setText("Pin code is sent to "+ch[0]+ch[1]+ch[2]+"***@gmail.com");
//            tvMesRegis.setVisibility(View.VISIBLE);
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
            e.printStackTrace();
            Toast.makeText(getActivity(), "Connection error, Please try again!", Toast.LENGTH_SHORT).show();
        }
    }



    public void checkUser(String us) {
        AccountService dataService = ApiUtil.getAccountService();
        Call<ResponseModel> callback = dataService.checkUsername(us);
        callback.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if(responseBody.getSuccess().equals("success")){
                        accept = true;
//                        System.out.println("---------------accept-checkU "+accept);
                    }else{
//                        System.out.println("---------------accept-checkU-fail"+accept);
                        Toast.makeText(getActivity(), "The username has been used", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    System.out.println("---------------accept-checkU=null"+accept);

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
        void apply(String username, String password);
    }

    private class EmailValidator {
        Pattern pattern;
        Matcher matcher;
        private static final String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

        public EmailValidator() {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }

        public boolean validate(final String hex) {
            matcher = pattern.matcher(hex);
            return matcher.matches();
        }
    }
}
