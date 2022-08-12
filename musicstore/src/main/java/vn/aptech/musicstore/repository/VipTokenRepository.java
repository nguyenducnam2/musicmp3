/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.VipToken;

/**
 *
 * @author Thanh Sang
 */
@Repository
public interface VipTokenRepository extends JpaRepository<VipToken, Long> {

    public VipToken findByToken(String token);

    public VipToken findByUserId(Long id);
}
