/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Artist;
import vn.aptech.musicstore.pagination.Paged;

/**
 *
 * @author namng
 */
public interface ArtistService {
    List<Artist> findAll();
    Optional<Artist> findById(int id);
    Artist save(Artist art);
    void deleteById(int id);
    List<Artist> findByNameCustom(String name);
    List<Artist> findTop12ByOrderByIdDesc();
    Paged<Artist> getPage(int pageNumber, int size);
}
