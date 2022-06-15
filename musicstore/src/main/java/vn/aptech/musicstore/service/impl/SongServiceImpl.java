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
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.repository.SongRepository;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@Service
public class SongServiceImpl implements SongService{
    
    @Autowired
    private SongRepository repo;

    @Override
    public List<Song> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Song> findById(int id) {
       return repo.findById(id);
    }

    @Override
    public Song save(Song s) {
        return repo.save(s);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Song> findByAlbumId(int albumId) {
       return repo.findByAlbumid(albumId);
    }

    @Override
    public List<Song> findByName(String name) {
       return repo.findByNameCustom(name);
    }

    @Override
    public List<Song> findByOrderByViewDesc() {
        return repo.findTop12ByOrderByViewDesc();
    }
    
}
