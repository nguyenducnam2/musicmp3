/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.Song;

/**
 *
 * @author namng
 */
public interface SongService {
    List<Song> findAll();
    Optional<Song> findById(int id);
    Song save(Song s);
    void deleteById(int id);
    List<Song> findByAlbumId(int albumId);
    List<Song> findByName(String name);
}
