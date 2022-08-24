/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.aptech.musicstore.entity.CartItem;

/**
 *
 * @author namng
 */
public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
    @Query("SELECT o FROM CartItem o WHERE o.cartId=:cartId")
    List<CartItem> findByCartId(@Param("cartId")int cartId);
}
