/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import vn.aptech.musicstore.entity.CartItem;

/**
 *
 * @author namng
 */
public interface CartItemService {

    List<CartItem> findAll();

    List<CartItem> findByCartId(int cartId);
    
    CartItem save(CartItem cartItem);
    
    void delete(CartItem cartItem);
}
