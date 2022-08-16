/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.SongOrder;
import vn.aptech.musicstore.pagination.Paged;

/**
 *
 * @author namng
 */
public interface SongOrderService {
    List<SongOrder> findAll();
    Optional<SongOrder> findById(int id);
    SongOrder save(SongOrder obj);
    void delete(SongOrder obj);
    Paged<SongOrder> getPage(int pageNumber, int size);
    List<SongOrder> getPageByDate(int pageNumber, int size,String from,String to);
}
