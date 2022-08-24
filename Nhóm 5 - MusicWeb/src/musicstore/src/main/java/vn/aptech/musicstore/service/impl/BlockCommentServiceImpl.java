/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.BlockComment;
import vn.aptech.musicstore.repository.BlockCommentRepository;
import vn.aptech.musicstore.service.BlockCommentService;

/**
 *
 * @author namng
 */
@Service
public class BlockCommentServiceImpl implements BlockCommentService {
    
    @Autowired
    private BlockCommentRepository repo;
    
    @Override
    public List<BlockComment> findAll() {
        return repo.findAll();
    }
    
    @Override
    public BlockComment save(BlockComment o) {
        return repo.save(o);
    }
    
    @Override
    public void delete(BlockComment o) {
        repo.delete(o);
    }
    
}
