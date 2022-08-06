/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Playlistitem;
import vn.aptech.musicstore.repository.PlaylistitemRepository;
import vn.aptech.musicstore.service.PlaylistitemService;

/**
 *
 * @author namng
 */
@Service
public class PlaylistitemServiceImpl implements PlaylistitemService {

    @Autowired
    private PlaylistitemRepository repo;

    @Override
    public List<Playlistitem> findByPlaylistId(int playlistId) {
        return repo.findByPlaylistId(playlistId);
    }

    @Override
    public Playlistitem save(Playlistitem plt) {
        return repo.save(plt);
    }

    @Override
    public void deleteBySongId(int id) {
        
    }

    @Override
    public void delete(Playlistitem plitem) {
        repo.delete(plitem);
    }

    @Override
    public List<Playlistitem> findAll() {
        return repo.findAll();
    }

}
