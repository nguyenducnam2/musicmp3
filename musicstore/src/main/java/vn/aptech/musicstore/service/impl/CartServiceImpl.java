/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.Cart;
import vn.aptech.musicstore.repository.CartRepository;
import vn.aptech.musicstore.service.CartService;

/**
 *
 * @author namng
 */
@Service
public class CartServiceImpl implements CartService{
    
    @Autowired
    private CartRepository repo;

    @Override
    public List<Cart> findAll() {
        return repo.findAll();
    }
    
    
}
