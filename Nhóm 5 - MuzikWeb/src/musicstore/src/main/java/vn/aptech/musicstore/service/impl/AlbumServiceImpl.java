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
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Album;
import vn.aptech.musicstore.pagination.Paged;
import vn.aptech.musicstore.pagination.Paging;
import vn.aptech.musicstore.repository.AlbumRepository;
import vn.aptech.musicstore.service.AlbumService;

/**
 *
 * @author namng
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository repo;

    @Override
    public List<Album> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Album> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Album save(Album alb) {
        return repo.save(alb);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return repo.existsById(id);
    }

    @Override
    public List<Album> findByNameCustom(String name) {
        return repo.findByNameCustom(name);
    }

    @Override
    public List<Album> findTop12() {
        return repo.findTop12ByOrderByIdDesc();
    }

    @Override
    public List<Album> findByArtistid(int id) {
       return repo.findByArtistid(id);
    }

    @Override
    public Paged<Album> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC,"id"));
        Page<Album> postPage = repo.findAll(request);
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }
}
