/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.musicstore.entity.Comment;

/**
 *
 * @author namng
 */
public interface CommentRepository extends JpaRepository<Comment,Integer>{
    List<Comment> findBySongId(int songId);
}
