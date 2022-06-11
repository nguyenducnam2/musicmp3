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
import vn.aptech.musicstore.entity.Genre;

/**
 *
 * @author namng
 */
public interface GenreRepository extends JpaRepository<Genre,Integer>{
      
    @Query("SELECT o FROM Genre o WHERE o.name LIKE CONCAT('%',:name,'%')")
    List<Genre> findByNameCustom(@Param("name") String name);
}
