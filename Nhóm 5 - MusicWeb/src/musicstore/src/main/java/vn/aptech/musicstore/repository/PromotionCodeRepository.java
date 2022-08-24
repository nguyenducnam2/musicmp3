/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.PromotionCode;

/**
 *
 * @author Administrator
 */
public interface PromotionCodeRepository extends JpaRepository<PromotionCode, Integer> {

    Optional<PromotionCode> findByCode(String code);

    List<PromotionCode> findByUserId(Long id);

    @Query("SELECT o FROM PromotionCode o WHERE o.code =:code AND o.userId=:userId")
    Optional<PromotionCode> findByCodeAndUserId(@Param("code") String code, @Param("userId") Long userId);
}
