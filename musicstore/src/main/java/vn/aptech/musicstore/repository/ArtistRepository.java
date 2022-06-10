/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.musicstore.entity.Artist;

/**
 *
 * @author namng
 */
public interface ArtistRepository extends JpaRepository<Artist,Integer>{
    
}
