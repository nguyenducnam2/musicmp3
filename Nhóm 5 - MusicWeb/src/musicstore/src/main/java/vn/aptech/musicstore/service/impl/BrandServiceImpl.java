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
import vn.aptech.musicstore.entity.Brand;
import vn.aptech.musicstore.repository.BrandRepository;
import vn.aptech.musicstore.service.BrandService;

/**
 *
 * @author pc
 */
@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
    private BrandRepository repo;

    @Override
    public List<Brand> findAll() {
        
        return repo.findAll();
        
    }

    @Override
    public Optional<Brand> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Brand save(Brand c) {
        return repo.save(c);
    }

    @Override
    public void deleteById(int id) {
repo.deleteById(id);
    }

    @Override
    public List<Brand> findByName(String name) {
        return repo.findByName(name);
    }
    
}
