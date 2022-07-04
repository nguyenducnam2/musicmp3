/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.VerificationToken;
import vn.aptech.musicstore.entity.model.UserModel;
import vn.aptech.musicstore.repository.AccountRepository;
import vn.aptech.musicstore.repository.VerificationTokenRepository;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author Thanh Sang
 */
@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository repo;

    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    
    @Override
    public List<Account> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Account save(Account account) {
        Optional<Account> accExist = repo.findByUsername(account.getUsername());

//        if (acc.isEmpty()) {
//            account.setPassword(encodePassword().encode(account.getPassword()));
//        return repo.save(account);
//        } else {
//            Account entity = acc.get();
//            entity.setUsername(account.getUsername());
//            entity.setPassword(account.getPassword());
//            entity.setFullname(account.getFullname());
//            entity.setRole(account.getRole());
//        return repo.save(entity);
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
        return repo.save(account);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> acc = repo.findByUsername(username);
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
        return repo.findByUsername(name);
    }

    @Override
    public Account registerUser(UserModel userModel) {
          Account acc = new Account();
        acc.setEmail(userModel.getEmail());
        acc.setFirstName(userModel.getFirstName());
        acc.setLastName(userModel.getLastName());
        acc.setRole("USER");
        acc.setPassword(encodePassword().encode(userModel.getPassword()));
        repo.save(acc);
        return acc;
    }

    @Override
    public void saveVerificationTokenForUser(String token, Account acc) {
         VerificationToken verificationToken = new VerificationToken(token,acc);
        
        verificationTokenRepository.save(verificationToken);
    }

}
