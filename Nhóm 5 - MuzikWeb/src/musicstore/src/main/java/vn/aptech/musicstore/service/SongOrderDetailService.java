/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.SongOrderDetail;

/**
 *
 * @author namng
 */
public interface SongOrderDetailService {

    List<SongOrderDetail> findAll();

    Optional<SongOrderDetail> findById(int id);

    SongOrderDetail save(SongOrderDetail obj);

    void delete(SongOrderDetail obj);

}
