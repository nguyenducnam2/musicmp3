/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.VerificationToken;

/**
 *
 * @author Administrator
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    public VerificationToken findByToken(String token);
    
//    @Query("SELECT o FROM Account o , VerificationToken v WHERE o.id:=v.acc.id ")
//    public Account findUserIdByToken(String token);

}
