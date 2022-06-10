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
import vn.aptech.musicstore.entity.Genre;
import vn.aptech.musicstore.repository.GenreRepository;
import vn.aptech.musicstore.service.GenreService;

/**
 *
 * @author namng
 */
@Service
public class GenreServiceImpl implements GenreService{
    @Autowired
    private GenreRepository repo;

    @Override
    public List<Genre> findAll() {
       return repo.findAll();
    }

    @Override
    public Optional<Genre> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Genre save(Genre genre) {
        return repo.save(genre);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }
    
    
}
