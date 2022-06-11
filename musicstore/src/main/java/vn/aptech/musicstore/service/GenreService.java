/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.Genre;

/**
 *
 * @author namng
 */
public interface GenreService {
    List<Genre> findAll();
    Optional<Genre> findById(int id);
    Genre save(Genre genre);
    void deleteById(int id);
    List<Genre> findByNameCustom(String name);
}
