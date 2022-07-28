/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Brand;


/**
 *
 * @author pc
 */
public interface BrandService {
    List<Brand> findAll();
    Optional<Brand> findById(int id);
    Brand save(Brand c);
        void deleteById(int id);
            List<Brand> findByName(String name);
}
