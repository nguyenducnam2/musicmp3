/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Product;

/**
 *
 * @author pc
 */
public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(int id);
    Product save(Product p);
    void deleteById(int id);
        List<Product> findByName(String name);

}
