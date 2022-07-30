/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Comment;
import vn.aptech.musicstore.repository.CommentRepository;
import vn.aptech.musicstore.service.CommentService;

/**
 *
 * @author namng
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository repo;

    @Override
    public List<Comment> findAll() {
       return repo.findAll();
    }

    @Override
    public List<Comment> findBySongId(int songId) {
        return repo.findBySongId(songId);
    }

    @Override
    public Optional<Comment> findById(int id) {
       return repo.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return repo.save(comment);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteBySongId(int songId) {
        for (Comment cmt: repo.findAll()) {
            if(cmt.getSongId()==songId){
                repo.delete(cmt);
            }
        }
    }
}