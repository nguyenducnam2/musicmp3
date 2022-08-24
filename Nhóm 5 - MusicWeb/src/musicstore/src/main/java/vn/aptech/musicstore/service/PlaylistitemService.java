/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import vn.aptech.musicstore.entity.Playlistitem;

/**
 *
 * @author namng
 */
public interface PlaylistitemService {

    List<Playlistitem> findAll();
    
    List<Playlistitem> findByPlaylistId(int playlistId);
    
    Playlistitem save(Playlistitem plt);
    
    void deleteBySongId(int id);
    
    void delete(Playlistitem plitem);

}
