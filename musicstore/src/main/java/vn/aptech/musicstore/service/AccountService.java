/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Account;

/**
 *
 * @author Thanh Sang
 */
public interface AccountService {
    List<Account> findAll();
    Optional<Account> findById(int id);
    Account save(Account account);
}
