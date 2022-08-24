/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Promotion;

/**
 *
 * @author Administrator
 */
@Service
public interface PromotionService {
      List<Promotion> findAll();

    Optional<Promotion> findById(int id);

     Optional<Promotion> findByCode(String code);
    Promotion save(Promotion obj);

    void delete(Promotion obj);
    
    String validatePromotionCode(String code);
}
