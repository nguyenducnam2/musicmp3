/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Subtitle;
import vn.aptech.musicstore.pagination.Paged;
import vn.aptech.musicstore.pagination.Paging;
import vn.aptech.musicstore.repository.SubtitleRepository;
import vn.aptech.musicstore.service.SubtitleService;

/**
 *
 * @author namng
 */
@Service
public class SubtitleServiceImpl implements SubtitleService {

    @Autowired
    private SubtitleRepository repo;

    @Override
    public List<Subtitle> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Subtitle> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Subtitle save(Subtitle subtitle) {
        return repo.save(subtitle);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Subtitle> findBySongId(int songId) {
        return repo.findBySongId(songId);
    }

    @Override
    public Paged<Subtitle> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Subtitle> postPage = repo.findAll(request);
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }

}
