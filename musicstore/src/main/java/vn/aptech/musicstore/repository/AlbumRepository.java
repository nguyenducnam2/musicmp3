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
import vn.aptech.musicstore.entity.Album;

/**
 *
 * @author namng
 */
public interface AlbumRepository extends JpaRepository<Album,Integer>{
    @Query("SELECT o FROM Album o WHERE o.name LIKE CONCAT('%',:name,'%')")
    List<Album> findByNameCustom(@Param("name") String name);
    
    List<Album> findTop12ByOrderByIdDesc();
}
