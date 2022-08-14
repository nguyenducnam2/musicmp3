/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Song;
import vn.aptech.musicstore.pagination.Paged;

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
    List<Song> findByOrderByViewDesc();
    List<Song> findByLyricCustom(String lyric);
    boolean existsById(int id);
    Paged<Song> getPage(int pageNumber, int size);
    List<Song> findByAccountId(Long accountId);
    List<Song> findByArtistId(int artistId);
}
