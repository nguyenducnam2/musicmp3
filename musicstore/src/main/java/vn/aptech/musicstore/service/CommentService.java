/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import java.util.Optional;
import vn.aptech.musicstore.entity.Comment;

/**
 *
 * @author namng
 */
public interface CommentService {

    List<Comment> findAll();
    Optional<Comment> findById(int id);
    List<Comment> findBySongId(int songId);
    Comment save(Comment comment);
    void deleteById(int id);
    void deleteBySongId(int songId);
}
