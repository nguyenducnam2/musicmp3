/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Subtitle;
import vn.aptech.musicstore.pagination.Paged;

/**
 *
 * @author namng
 */
public interface SubtitleService {
    List<Subtitle> findAll();
    Optional<Subtitle> findById(int id);
    Subtitle save(Subtitle subtitle);
    void deleteById(int id);
    List<Subtitle> findBySongId(int songId);
    Paged<Subtitle> getPage(int pageNumber, int size);
}
