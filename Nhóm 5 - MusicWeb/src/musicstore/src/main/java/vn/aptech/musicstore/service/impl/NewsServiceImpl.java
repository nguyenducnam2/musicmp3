/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import vn.aptech.musicstore.entity.News;
import vn.aptech.musicstore.pagination.Paged;
import vn.aptech.musicstore.pagination.Paging;
import vn.aptech.musicstore.repository.NewsRepository;
import vn.aptech.musicstore.service.NewsService;

/**
 *
 * @author Dung
 */
@Controller
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository repo;

    @Override
    public List<News> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<News> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public News save(News news) {
        return repo.save(news);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<News> findByTitleCustom(String title) {
        return repo.findByTitleCustom(title);
    }

    @Override
    public List<News> findTop12ByOrderByIdDesc() {
        return repo.findTop12ByOrderByIdDesc();
    }

    @Override
    public Paged<News> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC,"id"));
        Page<News> postPage = repo.findAll(request);
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }

}
