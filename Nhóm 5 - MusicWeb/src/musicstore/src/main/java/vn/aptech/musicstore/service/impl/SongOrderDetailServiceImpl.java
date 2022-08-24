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
import vn.aptech.musicstore.entity.SongOrderDetail;
import vn.aptech.musicstore.repository.SongOrderDetailRepository;
import vn.aptech.musicstore.service.SongOrderDetailService;

/**
 *
 * @author namng
 */
@Service 
public class SongOrderDetailServiceImpl implements SongOrderDetailService{
    
    @Autowired
    private SongOrderDetailRepository repo;

    @Override
    public List<SongOrderDetail> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<SongOrderDetail> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public SongOrderDetail save(SongOrderDetail obj) {
        return repo.save(obj);
    }

    @Override
    public void delete(SongOrderDetail obj) {
        repo.delete(obj);
    }
    
}
