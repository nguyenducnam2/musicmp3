package com.java.web_ecommerce_spring.services;

import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAll();
    Category save(Category category);
    void delete(int id);
    Category getCategoryByName(String name);
    Category getCategoryById(int id);
    List<Category> listCha();
    List<Category> listCon();
}
