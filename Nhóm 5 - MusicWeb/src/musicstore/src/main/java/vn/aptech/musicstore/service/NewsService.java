/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.News;
import vn.aptech.musicstore.pagination.Paged;

/**
 *
 * @author Dung
 */
public interface NewsService {
    List<News> findAll();
    Optional<News> findById(int id);
    News save(News news);
    void deleteById(int id);
    List<News> findByTitleCustom(String title);
    List<News> findTop12ByOrderByIdDesc();
    Paged<News> getPage(int pageNumber, int size);
}
