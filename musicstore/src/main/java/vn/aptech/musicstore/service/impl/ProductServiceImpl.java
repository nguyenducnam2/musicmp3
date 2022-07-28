/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Product;
import vn.aptech.musicstore.repository.ProductRepository;
import vn.aptech.musicstore.service.ProductService;

/**
 *
 * @author pc
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository repo;

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Product save(Product p) {
        return repo.save(p);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return repo.findByName(name);
    }
    
}
