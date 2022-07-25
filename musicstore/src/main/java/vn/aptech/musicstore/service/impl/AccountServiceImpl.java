/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
        VerificationToken verificationToken = new VerificationToken(acc,token);
        
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
            verificationTokenRepository.delete(verificationToken);
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
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public Account findAccountByEmail(String email) {
        return repoAccount.findByEmail(email);
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

}
