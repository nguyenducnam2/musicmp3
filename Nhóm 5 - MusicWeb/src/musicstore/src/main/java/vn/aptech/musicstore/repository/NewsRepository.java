/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.News;

/**
 *
 * @author Dung
 */
public interface NewsRepository extends JpaRepository<News, Integer>{
    @Query("SELECT o FROM News o WHERE o.title LIKE CONCAT('%',:title,'%')")
    List<News> findByTitleCustom(@Param("title") String title);
    
    List<News> findTop12ByOrderByIdDesc();
}
