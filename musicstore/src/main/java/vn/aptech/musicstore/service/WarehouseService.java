/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.WareHouse;

/**
 *
 * @author pc
 */
public interface WarehouseService {
    
    List<WareHouse> findAll();
    Optional<WareHouse> findById(int id);
    WareHouse save(WareHouse w);
        void deleteById(int id);
            List<WareHouse> findByName(String name);
}