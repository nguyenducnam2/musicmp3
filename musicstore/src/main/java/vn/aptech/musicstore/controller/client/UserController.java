/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.controller.client;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.PasswordResetToken;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.entity.VerificationToken;
import vn.aptech.musicstore.entity.model.PasswordModel;
import vn.aptech.musicstore.entity.model.UserModel;
import vn.aptech.musicstore.repository.PasswordResetTokenRepository;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private AccountService userService;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${uri.local}")
    private String uri_local;

//    @Autowired
//    private ApplicationEventPublisher publisher;
    @RequestMapping(method = RequestMethod.GET)
    public String index(Principal principal, Model model, HttpServletRequest request) {
        String username = principal.getName();
        Optional<Account> user = userService.findByUsername(username);

        HttpSession session = request.getSession();
        session.setAttribute("user", user.get());
        model.addAttribute("user", user.get());
        return "client/user/index";
    }

    @GetMapping("/profile")
    public String profile() {
        return "client/user/profile";
    }

//    @GetMapping("/register")
//    public String register() {
//        return "client/user/register";
//    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new Account());
        return "client/user/register";
    }

    @PostMapping("/registerProcess")
    public String registerProcess(Model model, @ModelAttribute UserModel userModel, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        Account user = userService.registerUser(userModel);
        String token = UUID.randomUUID().toString();
        System.out.println("token" + token);

        userService.saveVerificationTokenForUser(token, user);

        // Send Mail to acc
        String url = uri_local
                + "verifyRegistration?token="
                + token;

//        System.out.println("url" + url);
        userService.sendVerificationEmail(user, url);

        HttpSession session = request.getSession();
        session.setAttribute("createUserSuccess", "success");
        log.info("Click the link to verify your account: {}", url);
        return "client/user/register_success";
    }

//    @GetMapping("/verify")
//    public String verifyUser(@Param("token") String token) {
//        if (userService.validateVerificationToken(token).equals("valid")) {
//            return "client/user/verify_success";
//        } else {
//            return "client/user/verify_fail";
//        }
//    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "client/user/verify_success";
        }
        return "client/user/verify_fail";
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
            HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        VerificationToken verificationToken
                = userService.generateNewVerificationToken(oldToken);
        Account user = verificationToken.getAcc();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "client/user/register_success";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath();
    }

    private void resendVerificationTokenMail(Account user, String applicationUrl, VerificationToken verificationToken) throws MessagingException, UnsupportedEncodingException {
        String url
                = applicationUrl
                + "/verifyRegistration?token="
                + verificationToken.getToken();

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}",
                url);
        userService.sendVerificationEmail(user, url);
//        return url;
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
        Account user = userService.findAccountByEmail(passwordModel.getEmail());
        String url = "";
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenMail(user, applicationUrl(request), token);
        }
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,
            @RequestBody PasswordModel passwordModel) {
        String result = userService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid")) {
            return "Invalid Token";
        }
        Optional<Account> user = userService.getAccountByPasswordResetToken(token);
        if (user.isPresent()) {
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
            passwordResetTokenRepository.delete(passwordResetToken);
            return "Password Reset Successfully";
        } else {
            return "Invalid Token";
        }
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

    private String passwordResetTokenMail(Account user, String applicationUrl, String token) {
        String url
                = applicationUrl
                + "/savePassword?token="
                + token;

        //sendVerificationEmail()
        log.info("Click the link to Reset your Password: {}",
                url);

        return url;
    }

}
