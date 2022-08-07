/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.PasswordResetToken;
import vn.aptech.musicstore.entity.VerificationToken;
import vn.aptech.musicstore.entity.model.UserModel;
import vn.aptech.musicstore.repository.AccountRepository;
import vn.aptech.musicstore.repository.PasswordResetTokenRepository;
import vn.aptech.musicstore.repository.VerificationTokenRepository;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author Thanh Sang
 */
@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository repoAccount;

    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public List<Account> findAll() {
        return repoAccount.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repoAccount.findById(id);
    }

    @Override
    public Account save(Account account) {
        Optional<Account> accExist = repoAccount.findByUsername(account.getUsername());

//        if (acc.isEmpty()) {
//            account.setPassword(encodePassword().encode(account.getPassword()));
//        return repoAccount.save(account);
//        } else {
//            Account entity = acc.get();
//            entity.setUsername(account.getUsername());
//            entity.setPassword(account.getPassword());
//            entity.setFullname(account.getFullname());
//            entity.setRole(account.getRole());
//        return repoAccount.save(entity);
//        }
        if (accExist.isPresent()) {
            if (StringUtils.isEmpty(account.getPassword())) {
                account.setPassword(accExist.get().getPassword());
            } else {
                account.setPassword(encodePassword().encode(account.getPassword()));
            }
        } else {
            account.setPassword(encodePassword().encode(account.getPassword()));

        }
        return repoAccount.save(account);
    }

    @Override
    public void deleteById(Long id) {
        repoAccount.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> acc = repoAccount.findByUsername(username);
        if (acc.isEmpty()) {
            throw new UsernameNotFoundException("Account not found!");
        }
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        if (acc.isEmpty()) {
//            throw new UsernameNotFoundException("Account not found!");
//        } else {
//            GrantedAuthority au = new SimpleGrantedAuthority(acc.get().getRole());
//            authorities.add(au);
//            System.out.println("Role Account: " + acc.get().getRole());
//        }

//        else {
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
        return new AccountUserDetails(acc.get());
    }

    @Override
    public Optional<Account> findByUsername(String name) {
        return repoAccount.findByUsername(name);
    }

    @Override
    public Account registerUser(UserModel userModel) {
        Account acc = new Account();
        acc.setEmail(userModel.getEmail());
        acc.setUsername(userModel.getEmail());
        acc.setFirstName(userModel.getFirstName());
        acc.setLastName(userModel.getLastName());
        acc.setRole("ROLE_USER");
        acc.setPassword(encodePassword().encode(userModel.getPassword()));
        repoAccount.save(acc);
        return acc;
    }

    @Override
    public void saveVerificationTokenForUser(String token, Account acc) {
        VerificationToken verificationToken = new VerificationToken(acc, token);

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken
                = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            return "invalid";
        }

        Account user = verificationToken.getAcc();
        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {
//            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        user.setEnabled(true);
        repoAccount.save(user);
        return "valid";

    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setExpirationTime(calculateExpirationDate(10));
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }

    @Override
    public Account findAccountByEmail(String email) {
        return repoAccount.findByEmail(email);
    }

    @Override
    public String findByEmail(String email) {
        Account acc = repoAccount.findByEmail(email);
        return (acc == null) ? "Unique" : "Duplicate";
    }

    @Override
    public void createPasswordResetTokenForUser(Account user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken
                = passwordResetTokenRepository.findByToken(token);

        if (passwordResetToken == null) {
            return "invalid";
        }

        Account user = passwordResetToken.getUser();
        Calendar cal = Calendar.getInstance();

        if ((passwordResetToken.getExpirationTime().getTime()
                - cal.getTime().getTime()) <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }

        return "valid";

    }

    @Override
    public Optional<Account> getAccountByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(Account user, String newPassword) {
        user.setPassword(encodePassword().encode(newPassword));
        repoAccount.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(Account user, String oldPassword) {
        return encodePassword().matches(oldPassword, user.getPassword());
    }

    @Override
    public void sendVerificationEmail(Account user, String verifyUrl, String resetPwUrl)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "sluuthanh.demo.send@gmail.com";
        String senderName = "Muzik";
        String subject = "Please [[subject]]";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to [[content]]:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Muzik.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);

        if (!verifyUrl.equals("")) {
            subject = subject.replace("[[subject]]", "verify your registration");
            content = content.replace("[[content]]", "verify your registration");
            content = content.replace("[[URL]]", verifyUrl);
        }
        if (!resetPwUrl.equals("")) {
            subject = subject.replace("[[subject]]", "verify reset password");
            content = content.replace("[[content]]", "verify your email to reset password");
            content = content.replace("[[URL]]", resetPwUrl);
        }
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getFirstName() + " " + user.getLastName());
//        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
//        content = content.replace("[[URL_RESEND]]", resendUrl);
        helper.setText(content, true);

        mailSender.send(message);

    }

    
    // sau khi login bằng google account, 
    // chúng ta sẽ tạo 1 tài khoản local tương ứng để lưu thông tin
    public void processOAuthPostLogin(String username) {
        Optional<Account> user = repoAccount.findByUsername(username);
        // trường hợp chưa có tài khoản => tạo mới
        if (user.isEmpty()) {
            Account u = new Account();
            // thiếu phần setId();
            u.setUsername(username);
//            u.setProvider("GOOGLE");
            u.setEnabled(true);
            
            repoAccount.save(u);
        }
    }
}
