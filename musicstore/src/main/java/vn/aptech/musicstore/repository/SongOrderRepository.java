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
import vn.aptech.musicstore.entity.SongOrder;

/**
 *
 * @author namng
 */
public interface SongOrderRepository extends JpaRepository<SongOrder, Integer>{
    @Query("SELECT o FROM SongOrder o WHERE o.date BETWEEN :from AND :to")
    List<SongOrder> filterByDate(@Param("from")String from,@Param("to")String to);
}
