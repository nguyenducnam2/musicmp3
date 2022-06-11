/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.Artist;

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
}
