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
import vn.aptech.musicstore.repository.WarehouseRepository;
import vn.aptech.musicstore.service.WarehouseService;

import org.springframework.stereotype.Service;

import vn.aptech.musicstore.service.WarehouseService;

import vn.aptech.musicstore.repository.WarehouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import vn.aptech.musicstore.entity.WareHouse;

import vn.aptech.musicstore.repository.WarehouseRepository;



/**
 *
 * @author pc
 */
@Service
public class WarehouseServiceImpl implements WarehouseService{
    
    @Autowired
    private WarehouseRepository repo;

    @Override
    public List<WareHouse> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<WareHouse> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public WareHouse save(WareHouse w) {
        return repo.save(w);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<WareHouse> findByName(String name) {
        return null;
    }

   
   
    
}
