/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import vn.aptech.musicstore.entity.Product;
import vn.aptech.musicstore.service.ShoppingCartService;

/**
 *
 * @author Dung
 */
@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private Map<Integer, Product> map = new HashMap<Integer, Product>();

    @Override
    public void add(Product product) {
        Product existed=map.get(product.getId());
        if(existed==null){
            map.put(product.getId(), product);
        }
    }

    @Override
    public void remove(int id) {
        map.remove(id);
    }

    @Override
    public Collection<Product> getProducts() {
        return map.values();
    }
    
    @Override
    public void update(int id, int quantity){
        Product product=map.get(id);
        product.setQuantity(quantity);
        if(product.getQuantity() <= 0){
            map.remove(id);
        }
    }

    @Override
    public void clear() {
        map.clear();
    }
    
    @Override
    public double getAmount(){
        return map.values().stream().mapToDouble(product->product.getQuantity()*product.getPrice()).sum();
    }
    
    @Override
    public int getCount(){
        return map.values().size();
    }
}
