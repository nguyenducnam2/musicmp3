/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.UpgradeVipOrderDetails;
import vn.aptech.musicstore.repository.UpgradeVipOrderDetailsRepository;
import vn.aptech.musicstore.service.UpgradeVipOrderDetailsService;

/**
 *
 * @author Administrator
 */
@Service
public class UpgradeVipOrderDetailsServiceImpl implements UpgradeVipOrderDetailsService{
      @Autowired
    private UpgradeVipOrderDetailsRepository repo;

    @Override
    public List<UpgradeVipOrderDetails> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<UpgradeVipOrderDetails> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public UpgradeVipOrderDetails save(UpgradeVipOrderDetails obj) {
        return repo.save(obj);
    }

    @Override
    public void delete(UpgradeVipOrderDetails obj) {
        repo.delete(obj);
    }
}
