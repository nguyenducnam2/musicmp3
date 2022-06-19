/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.repository.AccountRepository;
import vn.aptech.musicstore.service.AccountService;

/**
 *
 * @author Thanh Sang
 */


@Service
public class AccountServiceImpl implements AccountService,UserDetailsService {

    @Autowired
    private AccountRepository repo;
    @Override
    public List<Account> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Account> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account save(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account acc = repo.findByUsername(username);
        if(acc == null){
            throw new UsernameNotFoundException("User not found!");
        }
        Account accRoles = repo.findRoleByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (accRoles != null && authorities.size() > 0) {
            GrantedAuthority au = new SimpleGrantedAuthority(accRoles.getRole());
            authorities.add(au);
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        boolean enabled= true;
            boolean accountNonExpired = true;
            boolean creadentialNonExpired = true;
            boolean accountNonLocked = true;
            
            return new org.springframework.security.core.userdetails.User(acc.getUsername(), acc.getPassword(), enabled, 
            accountNonExpired, creadentialNonExpired, accountNonExpired, authorities);
    }
    
}
