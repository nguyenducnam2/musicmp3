/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.UpgradeVipOrderDetails;

/**
 *
 * @author Administrator
 */
public interface UpgradeVipOrderDetailsService {

    List<UpgradeVipOrderDetails> findAll();

    Optional<UpgradeVipOrderDetails> findById(int id);

    UpgradeVipOrderDetails save(UpgradeVipOrderDetails obj);

    void delete(UpgradeVipOrderDetails obj);
}
