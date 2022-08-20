/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.PasswordResetToken;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.entity.VerificationToken;
import vn.aptech.musicstore.entity.model.PasswordModel;
import vn.aptech.musicstore.entity.model.UserModel;
import vn.aptech.musicstore.event.RegistrationCompleteEvent;
import vn.aptech.musicstore.repository.PasswordResetTokenRepository;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author Administrator
 */
@RestController
@Slf4j
@RequestMapping("/api")

public class RegistrationController {

    @Autowired
    private AccountService userService;

    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
//    @Autowired
//    private PasswordResetTokenRepository passwordResetTokenRepository;
//
//    @Value("${uri.local}")
//    private String uri_local;
//
//    @Autowired
//    private ApplicationEventPublisher publisher;
//

    @RequestMapping("/validateEmail")
    public @ResponseBody
    String checkEmailValidity(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        //Unique or Duplicate email
        return userService.findByEmail(email);
    }

    @PostMapping("/registerAndroid")
    public String registerUser(@RequestBody UserModel userModel) {
        userService.registerUser(userModel);
        return "Success";
    }

    @PostMapping("/loginAndroid")
    public Account login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Optional<Account> userAndroid = userService.findByUsername(username);
        if (userAndroid.isPresent()) {
            if (userService.checkIfValidOldPassword(userAndroid.get(), password)) {
                userAndroid.get().setPassword(password);
                return userAndroid.get();
            }
        }
        return null;
    }

    @GetMapping("/findByUsername")
    public Account findByUsername(@RequestParam("username") String username) {
        Optional<Account> userAndroid = userService.findByUsername(username);
        if (userAndroid.isPresent()) {
            return userAndroid.get();
        }
        return null;
    }

//
//    @GetMapping("/verifyRegistration")
//    public String verifyRegistration(@RequestParam("token") String token) {
//        String result = userService.validateVerificationToken(token);
//        if (result.equalsIgnoreCase("valid")) {
//            return "Account Verified Successfully";
//        }
//        return "Bad Account";
//    }
//
//    @GetMapping("/resendVerifyToken")
//    public String resendVerificationToken(@RequestParam("token") String oldToken,
//            HttpServletRequest request) {
//        VerificationToken verificationToken
//                = userService.generateNewVerificationToken(oldToken);
//        Account user = verificationToken.getAcc();
//        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
//        return "Verification Link Sent";
//    }
//
//    private String applicationUrl(HttpServletRequest request) {
//        return "http://"
//                + request.getServerName()
//                + ":"
//                + request.getServerPort()
//                + request.getContextPath();
//    }
//
//    private void resendVerificationTokenMail(Account user, String applicationUrl, VerificationToken verificationToken) {
//        String url
//                = applicationUrl
//                + "/verifyRegistration?token="
//                + verificationToken.getToken();
//
//        //sendVerificationEmail()
//        log.info("Click the link to verify your account: {}",
//                url);
//
//    }
//
//    @PostMapping("/resetPassword")
//    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
//        Account user = userService.findAccountByEmail(passwordModel.getEmail());
//        String url = "";
//        if (user != null) {
//            String token = UUID.randomUUID().toString();
//            userService.createPasswordResetTokenForUser(user, token);
//            url = passwordResetTokenMail(user, applicationUrl(request), token);
//        }
//        return url;
//    }
//
//    @PostMapping("/savePassword")
//    public String savePassword(@RequestParam("token") String token,
//            @RequestBody PasswordModel passwordModel) {
//        String result = userService.validatePasswordResetToken(token);
//        if (!result.equalsIgnoreCase("valid")) {
//            return "Invalid Token";
//        }
//        Optional<Account> user = userService.getAccountByPasswordResetToken(token);
//        if (user.isPresent()) {
//            userService.changePassword(user.get(), passwordModel.getNewPassword());
//            PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
//            passwordResetTokenRepository.delete(passwordResetToken);
//            return "Password Reset Successfully";
//        } else {
//            return "Invalid Token";
//        }
//    }
//
    @PostMapping("/resetPassword")
    public String savePassword(@RequestParam("username") String username,
            @RequestBody PasswordModel passwordModel) {
        Optional<Account> user = userService.findByUsername(username);
        if (user.isPresent()) {
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            return "Password Reset Successfully";
        }
        return "Failure";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel) {
        Account user = userService.findAccountByEmail(passwordModel.getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())) {
            return "Invalid Old Password";
        }
        //Save New Password
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password Changed Successfully";
    }
//
//    private String passwordResetTokenMail(Account user, String applicationUrl, String token) {
//        String url
//                = applicationUrl
//                + "/savePassword?token="
//                + token;
//
//        //sendVerificationEmail()
//        log.info("Click the link to Reset your Password: {}",
//                url);
//        return url;
//    }

}
