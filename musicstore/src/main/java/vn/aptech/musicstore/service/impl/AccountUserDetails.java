/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.aptech.musicstore.entity.Account;

/**
 *
 * @author Administrator
 */
public class AccountUserDetails implements UserDetails {

    Account acc;

    public AccountUserDetails(Account acc) {
        this.acc = acc;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    }

    @Override
    public String getPassword() {
        return acc.getPassword();
    }

    @Override
    public String getUsername() {
        return acc.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
