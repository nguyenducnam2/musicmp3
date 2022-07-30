/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Playlist;

/**
 *
 * @author namng
 */
public interface PlaylistService {

    List<Playlist> findAll();
    
    Optional<Playlist> findById(int id);

    List<Playlist> findByAccountId(Long accountId);
    
    Playlist save(Playlist playlist);
}
