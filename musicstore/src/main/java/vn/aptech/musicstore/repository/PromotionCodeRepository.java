/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.musicstore.entity.PromotionCode;

/**
 *
 * @author Administrator
 */

public interface PromotionCodeRepository extends JpaRepository<PromotionCode, Integer>{
    Optional<PromotionCode> findByCode(String code);
}
