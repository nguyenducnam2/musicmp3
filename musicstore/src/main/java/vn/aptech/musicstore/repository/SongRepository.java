/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.Song;

/**
 *
 * @author namng
 */
public interface SongRepository extends JpaRepository<Song,Integer>{
    
    @Query("SELECT o FROM Song o WHERE o.albumId=:albumId")
    List<Song> findByAlbumid(@Param("albumId") int albumId);
    
    @Query("SELECT o FROM Song o WHERE o.name LIKE CONCAT('%',:name,'%')")
    List<Song> findByNameCustom(@Param("name") String name);
    
    List<Song> findTop12ByOrderByViewDesc();
    
    @Query("SELECT o FROM Song o WHERE o.lyric LIKE CONCAT('%',:lyric,'%')")
    List<Song> findByLyricCustom(@Param("lyric") String lyric);
}
