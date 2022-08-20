/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.PromotionCode;
import vn.aptech.musicstore.repository.PromotionCodeRepository;
import vn.aptech.musicstore.service.PromotionCodeService;

/**
 *
 * @author Administrator
 */


@Service
public class PromotionCodeServiceImpl implements PromotionCodeService{
     @Autowired
    private PromotionCodeRepository repo;

    @Override
    public List<PromotionCode> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<PromotionCode> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public PromotionCode save(PromotionCode obj) {
        return repo.save(obj);
    }

    @Override
    public void delete(PromotionCode obj) {
        repo.delete(obj);
    }

    @Override
    public Optional<PromotionCode> findByCode(String code) {
        return repo.findByCode(code);
    }
}
