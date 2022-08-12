/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.VerificationToken;
import vn.aptech.musicstore.entity.model.UserModel;

/**
 *
 * @author Thanh Sang
 */
public interface AccountService {

    List<Account> findAll();

    Optional<Account> findById(Long id);

    Optional<Account> findByUsername(String name);

    Account save(Account account);

    void deleteById(Long id);
    
    Account registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, Account acc);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    Account findAccountByEmail(String email);
    String findByEmail(String email);

    void createPasswordResetTokenForUser(Account user, String token);

    String validatePasswordResetToken(String token);

    Optional<Account> getAccountByPasswordResetToken(String token);

    void changePassword(Account get, String newPassword);

    boolean checkIfValidOldPassword(Account user, String oldPassword);
    
//      public void register(User user, String siteURL) {
//     
//    }
     
    void sendVerificationEmail(Account user, String verifyUrl, String resendUrl)  throws MessagingException, UnsupportedEncodingException ;
    
    Date calculateExpirationDate(int expirationTime);
}
