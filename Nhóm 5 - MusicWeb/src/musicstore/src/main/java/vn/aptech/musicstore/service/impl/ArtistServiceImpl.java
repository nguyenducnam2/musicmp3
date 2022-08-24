/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import vn.aptech.musicstore.entity.Artist;
import vn.aptech.musicstore.pagination.Paged;
import vn.aptech.musicstore.pagination.Paging;
import vn.aptech.musicstore.repository.ArtistRepository;
import vn.aptech.musicstore.service.ArtistService;

/**
 *
 * @author namng
 */
@Controller
public class ArtistServiceImpl implements ArtistService{
    
    @Autowired
    private ArtistRepository repo;

    @Override
    public List<Artist> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Artist> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Artist save(Artist art) {
        return repo.save(art);
    }

    @Override
    public void deleteById(int id) {
       repo.deleteById(id);
    }

    @Override
    public List<Artist> findByNameCustom(String name) {
        return repo.findByNameCustom(name);
    }

    @Override
    public List<Artist> findTop12ByOrderByIdDesc() {
        return repo.findTop12ByOrderByIdDesc();
    }

    @Override
    public Paged<Artist> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC,"id"));
        Page<Artist> postPage = repo.findAll(request);
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }
    
}
