package com.java.web_ecommerce_spring.services;

import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAll();
    Product save(Product product);
    void delete(int id);
    Product getProductByName(String name);
    Product getProductById(int id);
    List<Product> getTopProduct(Sort sort);
    List<Product> getProductByDiscount(int discount,Sort sort);
    List<Product> getProductByCategory(Category category);
    Page<Product> findProductByCategory(Category category, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findProductByNameLike(String name,Pageable pageable);
    List<Product> findProductByNameLike(String name);
    int updatepro(int quantity, int id);
    List<Integer> listID();
    List<Product> listNotSell();
}
