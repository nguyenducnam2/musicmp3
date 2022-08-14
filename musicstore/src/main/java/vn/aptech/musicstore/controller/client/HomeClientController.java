/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.client;

import java.io.UnsupportedEncodingException;
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
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.PasswordResetToken;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.entity.VerificationToken;
import vn.aptech.musicstore.entity.model.PasswordModel;
import vn.aptech.musicstore.entity.model.UserModel;
import vn.aptech.musicstore.repository.PasswordResetTokenRepository;
import vn.aptech.musicstore.repository.VerificationTokenRepository;
import vn.aptech.musicstore.service.AccountService;
import vn.aptech.musicstore.service.AlbumService;
import vn.aptech.musicstore.service.ArtistService;
import vn.aptech.musicstore.service.SongService;
import vn.aptech.musicstore.service.NewsService;

/**
 *
 * @author namng
 */
@Controller
@Slf4j
public class HomeClientController implements ErrorController {

    @Autowired
    private AccountService userService;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private SongService service_song;

    @Autowired
    private AlbumService service_album;

    @Autowired
    private ArtistService service_artist;

//    @Value("${uri.local}")
//    private String uri_local;
    @Autowired
    private NewsService service_news;

    @GetMapping
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute("user", session.getAttribute("user"));
            model.addAttribute("user", session.getAttribute("user"));
        } else {
            model.addAttribute("user", null);
        }
        List<Song> listsong = new ArrayList<>();
        for (int i = service_song.findAll().size() - 1; i >= 0; i--) {
            listsong.add(service_song.findAll().get(i));
        }
        model.addAttribute("listsong", listsong);
        model.addAttribute("service",service_song);
        model.addAttribute("listsong_hot", service_song.findByOrderByViewDesc());
        model.addAttribute("listalbum", service_album.findTop12());
        model.addAttribute("listartist", service_artist.findTop12ByOrderByIdDesc());
        model.addAttribute("listnews", service_news.findTop12ByOrderByIdDesc());
        return "client/index";
    }

    @GetMapping("/result")
    public String result(Model model, @RequestParam("searchname") String searchname,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute("user", session.getAttribute("user"));
            model.addAttribute("user", session.getAttribute("user"));
        } else {
            model.addAttribute("user", null);
        }
        List<Song> listsong = service_song.findByName(searchname);
        if (service_song.findByLyricCustom(searchname).size() > 0) {
            listsong.addAll(service_song.findByLyricCustom(searchname));
        }
        model.addAttribute("listsong", listsong);
        model.addAttribute("searchname", searchname);
        model.addAttribute("listalbum", service_album.findByNameCustom(searchname));
        model.addAttribute("listartist", service_artist.findByNameCustom(searchname));
        model.addAttribute("listnews", service_news.findByTitleCustom(searchname));
        return "client/result";
    }

    @GetMapping("/contact")
    public String contact(Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute("user", session.getAttribute("user"));
            model.addAttribute("user", session.getAttribute("user"));
        } else {
            model.addAttribute("user", null);
        }
        return "client/contactUs/contact";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        return "client/login";
    }

    @GetMapping(value = "/403")
    public String error403(Model model) {
        return "error/403";
    }

    @GetMapping("/error")
    public String handleError() {
        //do something like logging
        return "error/403";
    }

//    @GetMapping("/register")
//    public String register() {
//        return "client/register";
//    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("account", new UserModel());
        return "client/register";
    }

    @PostMapping("/registerProcess")
    public String registerProcess(Model model, @ModelAttribute UserModel userModel, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        if (userService.findByEmail(userModel.getEmail()).equals("Unique")) {
            Account user = userService.registerUser(userModel);
            String token = UUID.randomUUID().toString();
            System.out.println("token: " + token);

            userService.saveVerificationTokenForUser(token, user);

//        String url = uri_local
            String url = applicationUrl(request)
                    + "/verifyRegistration?token="
                    + token;

            String resend_url = null;
//                    = applicationUrl(request)
//                    + "/resendVerifyToken?token="
//                    + token;

            // Send Mail to acc
            userService.sendVerificationEmail(user, url, "");

            model.addAttribute("email", userModel.getEmail());
            HttpSession session = request.getSession();
            session.setAttribute("createUserSuccess", "success");
            log.info("Click the link to verify your account: {}", url);
            return "client/pages-confirm-mail";
        } else {
            return "client/index";
        }
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(Model model, @RequestParam("token") String token, HttpServletRequest request) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            verificationTokenRepository.delete(verificationTokenRepository.findByToken(token));
            model.addAttribute("verify", "register");
            return "client/verify_success";
        }
        if (result.equals("expired")) {
            model.addAttribute("oldToken", token);
            model.addAttribute("verify", "register");
            return "client/verify_fail";
        }
        model.addAttribute("verify", "register");
        return "client/verify_fail";
    }

    @RequestMapping("/registerValidateEmail")
    public @ResponseBody
    String checkEmailValidity(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        return userService.findByEmail(email);
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
            HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        VerificationToken verificationToken
                = userService.generateNewVerificationToken(oldToken);
        Account user = verificationToken.getAcc();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "client/pages-confirm-mail";
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

//        String resend_url
//                = applicationUrl
//                + "/resendVerifyToken?token="
//                + verificationToken.getToken();
        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}",
                url);
        userService.sendVerificationEmail(user, url, "");
//        return url;
    }

    @GetMapping("/resetPassword")
    public String resetPassword(Model model) {
        model.addAttribute("account", new PasswordModel());
        return "client/reset_pw_email";
    }

    @PostMapping("/resetPasswordProcess")
    public String resetPassword(Model model, @ModelAttribute("passwordModel") PasswordModel passwordModel, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("emailReset");
        Account user = userService.findAccountByEmail(email);
        String resetUrl = "";
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
//            url = passwordResetTokenMail(user, applicationUrl(request), token);
            resetUrl = applicationUrl(request)
                    + "/savePassword?token="
                    + token;
            userService.sendVerificationEmail(user, "", resetUrl);
            model.addAttribute("email", email);
            return "client/pages-confirm-mail-reset-pw";
        }
        return "client/reset_pw_email";
    }

//    private String passwordResetTokenMail(Account user, String applicationUrl, String token) throws MessagingException, UnsupportedEncodingException {
//        String url
//                = applicationUrl
//                + "/savePassword?token="
//                + token;
//
//        //sendVerificationEmail()
//        log.info("Click the link to Reset your Password: {}",
//                url);
//        userService.sendVerificationEmail(user, null, url);
//        return "client/pages-confirm-mail-reset-pw";
//    }
    @GetMapping("/savePassword")
    public String savePassword(Model model, @RequestParam("token") String token,
            @ModelAttribute PasswordModel passwordModel) {
        String result = userService.validatePasswordResetToken(token);
        System.out.println("result: " + result);
        if (!result.equalsIgnoreCase("valid")) {
            model.addAttribute("verify", "resetPassword");
            return "client/verify_fail";
        }
//      
        return "redirect:/resetChangePassword?token=" + token;
    }

    @GetMapping("/resetChangePassword")
    public String resetChangePassword(Model model, @RequestParam("token") String token) {
//        String result = userService.validatePasswordResetToken(token);
        Optional<Account> user = userService.getAccountByPasswordResetToken(token);
        model.addAttribute("token", token);
        PasswordModel passwordModel = new PasswordModel();
        passwordModel.setEmail(user.get().getEmail());
        model.addAttribute("passwordModel", passwordModel);

        return "client/reset_change_pass";
    }

    @PostMapping("/resetChangePasswordProcess")
    public String resetChangePassword(Model model, @ModelAttribute("passwordModel") PasswordModel passwordModel, HttpServletRequest request) {
        String token = request.getParameter("token");
        Optional<Account> user = userService.findByUsername(passwordModel.getEmail());
        System.out.println("token-email : " + token + passwordModel.getEmail());
        if (user.isPresent()) {
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
            passwordResetTokenRepository.delete(passwordResetToken);
            model.addAttribute("verify", "resetPassword");
            return "client/verify_success";
        }
        model.addAttribute("fail", "Change password fails!!");
        return "client/reset_change_pass";
    }

//    test api
//    @PostMapping("/changePassword")
//    public String changePassword(@RequestBody PasswordModel passwordModel) {
//        Account user = userService.findAccountByEmail(passwordModel.getEmail());
//        if (!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())) {
//            return "Invalid Old Password";
//        }
//        //Save New Password
//        userService.changePassword(user, passwordModel.getNewPassword());
//        return "Password Changed Successfully";
//    }
}
