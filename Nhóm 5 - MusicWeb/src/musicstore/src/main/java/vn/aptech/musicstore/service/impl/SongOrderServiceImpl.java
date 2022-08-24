/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.SongOrder;
import vn.aptech.musicstore.pagination.Paged;
import vn.aptech.musicstore.pagination.Paging;
import vn.aptech.musicstore.repository.SongOrderRepository;
import vn.aptech.musicstore.service.SongOrderService;

/**
 *
 * @author namng
 */
@Service
public class SongOrderServiceImpl implements SongOrderService {

    @Autowired
    private SongOrderRepository repo;

    @Override
    public List<SongOrder> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<SongOrder> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public SongOrder save(SongOrder obj) {
        return repo.save(obj);
    }

    @Override
    public void delete(SongOrder obj) {
        repo.delete(obj);
    }

    @Override
    public Paged<SongOrder> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<SongOrder> postPage = repo.findAll(request);
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }

    @Override
    public List<SongOrder> getPageByDate(int pageNumber, int size, Date from, Date to) {
        return repo.filterByDate(from, to);
    }

}
