/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.Album;

/**
 *
 * @author namng
 */
public interface AlbumService {
    List<Album> findAll();
    Optional<Album> findById(int id);
    Album save(Album alb);
    void deleteById(int id);
    boolean existsById(int id);
    List<Album> findByNameCustom(String name);
}
