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
import vn.aptech.musicstore.pagination.Paged;
import vn.aptech.musicstore.repository.UploadRepository;
import vn.aptech.musicstore.service.UploadService;

/**
 *
 * @author pc
 */
@Service
public class UploadServiceImpl implements UploadService {
    
    @Autowired
    private UploadRepository repo;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Song> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Song> findByOrderByViewDesc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Song> findByLyricCustom(String lyric) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paged<Song> getPage(int pageNumber, int size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
