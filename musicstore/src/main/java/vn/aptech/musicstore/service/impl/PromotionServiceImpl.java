/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Account;
import vn.aptech.musicstore.entity.Promotion;
import vn.aptech.musicstore.repository.PromotionRepository;
import vn.aptech.musicstore.repository.UpgradeVipOrderDetailsRepository;
import vn.aptech.musicstore.service.PromotionService;

/**
 *
 * @author Administrator
 */

@Service
public class PromotionServiceImpl implements PromotionService{
     @Autowired
    private PromotionRepository repo;

    @Override
    public List<Promotion> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Promotion> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Promotion save(Promotion obj) {
        return repo.save(obj);
    }

    @Override
    public void delete(Promotion obj) {
        repo.delete(obj);
    }
    
    @Override
    public String validatePromotionCode(String code) {
         Optional<Promotion> promotion
                = repo.findByCode(code);

        if (promotion == null) {
            return "invalid";
        }

        Calendar cal = Calendar.getInstance();

        if ((promotion.get().getEndDate().getTime()
                - cal.getTime().getTime()) <= 0) {
//            verificationTokenRepository.delete(vipToken);
            return "expired";
        }

        return "valid";

    }

    @Override
    public Optional<Promotion> findByCode(String code) {
        return repo.findByCode(code);
    }
}
