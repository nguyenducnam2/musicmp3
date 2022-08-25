/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.CartItem;
import vn.aptech.musicstore.repository.CartItemRepository;
import vn.aptech.musicstore.service.CartItemService;

/**
 *
 * @author namng
 */
@Service
public class CartItemServiceImpl implements CartItemService{
    
    @Autowired
    private CartItemRepository repo;

    @Override
    public List<CartItem> findAll() {
        return repo.findAll();
    }

    @Override
    public List<CartItem> findByCartId(int cartId) {
        return repo.findByCartId(cartId);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return repo.save(cartItem);
    }

    @Override
    public void delete(CartItem cartItem) {
        repo.delete(cartItem);
    }
    
}
