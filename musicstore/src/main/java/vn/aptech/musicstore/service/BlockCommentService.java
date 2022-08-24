/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import vn.aptech.musicstore.entity.BlockComment;

/**
 *
 * @author namng
 */
public interface BlockCommentService {
    
    List<BlockComment> findAll();

    BlockComment save(BlockComment o);
    
    void delete(BlockComment o);
}
