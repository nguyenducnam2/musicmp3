/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.musicstore.entity.Cart;
import vn.aptech.musicstore.entity.CartItem;
import vn.aptech.musicstore.service.AccountService;
import vn.aptech.musicstore.service.CartItemService;
import vn.aptech.musicstore.service.CartService;
import vn.aptech.musicstore.service.SongService;

/**
 *
 * @author namng
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    @Autowired
    private CartService service;

    @Autowired
    private CartItemService service_ci;

    @Autowired
    private AccountService service_acc;

    @Autowired
    private SongService service_song;

    @GetMapping
    public List<Cart> findAll() {
        return service.findAll();
    }

    @GetMapping("/items")
    public List<CartItem> findItem() {
        return service_ci.findAll();
    }

    @PostMapping("/add")
    public Cart save(@RequestParam("accountId") Long accountId, @RequestParam("songId") int songId) {
        boolean exist = false;
        for (Cart item : service.findAll()) {
            if (item.getAccountId() == accountId) {
                exist = true;
            }
        }
        if (exist == false) {
            Cart cart = new Cart();
            cart.setAccountId(accountId);
            cart.setAccount(service_acc.findById(accountId).get());
            cart.setAmount(1);
            cart = service.save(cart);
            CartItem ci = new CartItem();
            ci.setSongId(songId);
            ci.setSong(service_song.findById(songId).get());
            ci.setCartId(cart.getId());
            ci.setCart(cart);
            service_ci.save(ci);
            return cart;
        } else {
            Cart cart = service.findByAccountId(accountId);
            cart.setAmount(cart.getAmount() + 1);
            CartItem ci = new CartItem();
            ci.setSongId(songId);
            ci.setSong(service_song.findById(songId).get());
            ci.setCartId(cart.getId());
            ci.setCart(cart);
            service_ci.save(ci);
            return service.save(cart);
        }
    }

    @GetMapping("/findItemsofCartByAccountId")
    public List<CartItem> findItemsofCartByAccountId(@RequestParam("accountId") Long accountId) {
        List<CartItem> list = new ArrayList<>();
        try {
            list = service_ci.findByCartId(service.findByAccountId(accountId).getId());
            if (list.size() == 0) {
                return list;
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    @DeleteMapping("/deleteItem")
    public List<CartItem> deleteItem(@RequestParam("accountId") Long accountId, @RequestParam("songId") int songId) {
        Cart cart = service.findByAccountId(accountId);
        List<CartItem> list = service_ci.findByCartId(cart.getId());
        for (CartItem item : list) {
            if (item.getSongId() == songId) {
                service_ci.delete(item);
            }
        }
        cart.setAmount(service_ci.findByCartId(cart.getId()).size());
        if (cart.getAmount() > 0) {
            service.save(cart);
        }else{
            service.delete(cart);
        }
        return null;
    }
}
