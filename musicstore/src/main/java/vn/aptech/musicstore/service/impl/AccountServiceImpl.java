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
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.repository.AccountRepository;
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

    @Override
    public List<Account> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Account> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Account save(Account account) {
        Account acc = repo.findByUsername(account.getUsername());
        if (acc == null) {
            account.setPassword(encodePassword().encode(account.getPassword()));
        return repo.save(account);
        } else {
            acc.setUsername(account.getUsername());
            acc.setPassword(account.getPassword());
            acc.setFullname(account.getFullname());
            acc.setRole(account.getRole());
        return repo.save(acc);
        }
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account acc = repo.findByUsername(username);
        Set<GrantedAuthority> authorities = new HashSet<>();
        if (acc == null) {
            throw new UsernameNotFoundException("User not found!");
        } else {
            GrantedAuthority au = new SimpleGrantedAuthority(acc.getRole());
            authorities.add(au);
            System.out.println("Role Account: " + acc.getRole());
        }
//        else {
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean creadentialNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(acc.getUsername(), acc.getPassword(), enabled,
                accountNonExpired, creadentialNonExpired, accountNonLocked, authorities);
    }

    @Override
    public Account findByUsername(String name) {
        return repo.findByUsername(name);
    }

}
