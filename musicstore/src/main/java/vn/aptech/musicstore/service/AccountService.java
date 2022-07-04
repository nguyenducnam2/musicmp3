/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Account;
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
    
    public Account registerUser(UserModel userModel);

    public void saveVerificationTokenForUser(String token, Account acc);
}
