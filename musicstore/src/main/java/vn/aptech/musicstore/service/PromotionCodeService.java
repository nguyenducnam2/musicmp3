/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.PromotionCode;

/**
 *
 * @author Administrator
 */

@Service
public interface PromotionCodeService {
       List<PromotionCode> findAll();

    Optional<PromotionCode> findById(int id);

    Optional<PromotionCode> findByCode(String code);
    PromotionCode save(PromotionCode obj);

    void delete(PromotionCode obj);
}
