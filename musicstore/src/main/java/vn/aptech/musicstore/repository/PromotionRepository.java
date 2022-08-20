/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.musicstore.entity.Promotion;

/**
 *
 * @author Administrator
 */

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    Promotion findByCode(String code);
}
