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
import vn.aptech.musicstore.entity.Playlist;
import vn.aptech.musicstore.repository.PlaylistRepository;
import vn.aptech.musicstore.service.PlaylistService;

/**
 *
 * @author namng
 */
@Service
public class PlaylistServiceImpl implements PlaylistService{

    @Autowired
    private PlaylistRepository repo;
    
    @Override
    public List<Playlist> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Playlist> findByAccountId(Long accountId) {
       return repo.findByAccountId(accountId);
    }

    @Override
    public Playlist save(Playlist playlist) {
        return repo.save(playlist);
    }

    @Override
    public Optional<Playlist> findById(int id) {
       return repo.findById(id);
    }
    
}
